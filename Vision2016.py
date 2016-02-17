import numpy as np
import cv2
import socket
import sys
import math
import os

def get_image(): #can be initialized in the function itself, it is fine here.
    retval, img = camera.read()
    return img
############################################################################################################
def getDistFromCenter():
	showPrint = False
	
	opener = urllib2.build_opener()
	page = opener.open(picurl)
	pic =page.read()
	fout = open('image.jpg', 'wb')
	fout.write(pic)
	fout.close()

	img = cv2.imread('image.jpg')
	GlobalWidth = 640
	GlobalHeight = 480

	resize = False
	if resize: 
		img = cv2.resize(img, (GlobalWidth/2,GlobalHeight/2))
		GlobalWidth = GlobalWidth/2
		GlobalHeight = GlobalHeight/2

	if showPrint: cv2.imshow('original_img', img)

	blue_only = processor.get_blue_hue(img)
	goals_img = blue_only.copy()
	
	contours, hierarchy = cv2.findContours(blue_only, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
	os.system('clear')
	area = 0
	idx = -1
	for i,cnt in enumerate(contours):
		if (800 < cv2.contourArea(cnt) < 20000):
			rect = cv2.convexHull(cnt)
			minRect = cv2.minAreaRect(rect)

			
			x1 = int(minRect[0][0])
			y1 = int(minRect[0][1])
			width = minRect[1][0]
			height = minRect[1][1]
			degree = minRect[2]
			if showPrint:

				print 'x1:', x1
				print 'y1:', y1
				print 'width:', width
				print 'height:', height
				print 'degree:', degree
			
			if showPrint:
				pass

			if minRect[1][0]:
				ratio = minRect[1][1] / minRect[1][0]

			else:
				ratio = 0

			if showPrint: print 'ratio', ratio

			if ((2.9 < ratio < 3.3) or (0.25 < ratio < 0.37)):

				if showPrint: print 'winning ratio:', ratio

				if (area < cv2.contourArea(cnt)):
					idx = i
					
	if (idx != -1):

		if showPrint:		
			cv2.drawContours(goals_img, contours, idx, (50, 255, 60), 3)
			cv2.imshow('rects', goals_img)
		
		rect = cv2.convexHull(contours[idx])

		if showPrint: 'rect:', rect
		minRect = cv2.minAreaRect(rect)
		
		area = cv2.contourArea(contours[idx])
		
		if showPrint:
			pass
		x1 = int(minRect[0][0])
		y1 = int(minRect[0][1])
		width = minRect[1][0]
		height = minRect[1][1]
		degree = minRect[2]

		if showPrint: print 'DEGREE:', degree

		if width < height:
			width, height = height, width

		if showPrint: print width
		dist_FOV = 4.25*GlobalWidth/width

		if showPrint: print dist_FOV
		dist_to_wall = (dist_FOV/2) / 0.41237445509
		new_dist_to_wall = dist_to_wall * (0.178195*np.log(dist_to_wall) + 0.6357449)
		dist_to_wall = int(new_dist_to_wall*1000)

		if showPrint:
			print 'CALCULATED DIST', new_dist_to_wall
		ratio = height/width

		if showPrint: 
			cv2.circle(goals_img, (x1,y1), 6, (244,255,255))
			cv2.line(goals_img, (GlobalWidth/2, GlobalHeight), (GlobalWidth/2, 0), (200,200,200)) 
			cv2.imshow('rects', goals_img)
		dist = 0

		if (x1 < GlobalWidth/2):
			dist = -(GlobalWidth/2 - x1)

		elif (x1 > GlobalWidth/2):
			dist = (GlobalWidth/2 - (GlobalWidth - x1))

		elif (x1 == GlobalWidth/2):
			dist = 0	
		return str(dist) + ',' + str(x1) + ',' + str(y1) + ',' + str(dist_to_wall), goals_img

	else:
		return 'n', goals_img
############################################################################################################
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
