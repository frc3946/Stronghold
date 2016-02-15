import numpy as np
import cv2
import socket
import sys
import math
import os

def get_image(): #can be initialized in the function itself, it is fine here.
    retval, img = camera.read()
    return img

if __name__ == '__main__':
    
    #create the socket server
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    serv_addr = ('127.0.0.1', 10005)
    print 'starting server on: ', serv_addr[0], ':', serv_addr[1]
    sock.bind(serv_addr)
    sock.listen(1)
    conn = None
    while True:
            try:
                    conn, cli_addr = sock.accept()
                    print 'connection from: ', cli_addr

                    try:
                            while True:
                                    try:
                                            recvd = conn.recv(4096)
                                            print recvd

                                            if ('G' in recvd):
                                                        #if the pictures are not coming out as desired, ramp_frames
                                                        #will provide the number of frames to discard prior to the actual frame used.
                                                        camera = cv2.VideoCapture(0) #opens camera
                                                        #runs the capture image thing
                                                        camera_capture = get_image() 
                                                        #write image to a file
                                                        file= "E:\img.jpg"
                                                        cv2.imwrite(file, camera_capture)
                                                        #destroy camera
                                                        del(camera)
                                                        cv2.imshow('pic', camera_capture)
                                                        cv2.waitKey(0)
                                                                                                        
                                            else:
                                                    print 'not G'
                                    except:
                                            print ' exception:', sys.exc_info()[0]
                                            conn.close()
                                            print 'conn closed'
                                            break
                    finally:
                           print 'connection closed'
                           conn.close()

            except KeyboardInterrupt:
                        print 'LOSING'
                        if conn: conn.close()
                        break         
