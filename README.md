CrimeSceneInvestigation
=======================

Java project file from Advanced Java Concepts that requires searching through a .raw file and then gathering information to produce a jpeg. 

The Task

A digital camera has been found, but there do not appear to be any pictures on it. Perhaps they have been deleted. Of course, the camera's flash card has data on it, even if the data is not readily accessible. The contents of the camera's flash card is given to you to see if you can recover any of the pictures.
Your task is to write a Java program to recover as many pictures as possible from the flash card data 80MB. Take a picture of yourself at any one of the scenes found in the pictures.

You need to know that all pictures taken by this camera are jpeg images. Also, jpeg viewing software does not care if a jpeg file has extraneous bytes at the end of the file because the size of the image data is encoded in the jpeg format. You are asked to recover each jpeg picture in a file of its own, but extraneous bytes at the end of the file are allowed. As a consequence it is not necessary to actually write a program that fully parses the jpeg format (this is quite difficult).

This camera formats the flash card in blocks of 512 bytes. Each picture the camera takes starts at the begining of a block and is stored in contiguous blocks. If the picture does not fit exactly into an even number of blocks (and it hardly ever would), the rest of the last block is not used. Deleting a picture marks the picture's blocks as available to be reused. Hopefully, no pictures have been deleted from the flash card before another picture was taken. If this is the case, none of the blocks have been used to store more than one picture since the flash card was new.

Note

My camera broke and now I have a different camera. Try to make you program detect any jpg image (assuming the camera manufactor follows the jpg standard).
Input/Output

The program should expect one command-line argument --- the URL of the (binary) flash card image. So, the program might be invoked as follows:
java Recover http://www.cs.fit.edu/~ryan/cse4051/projects/csi/card.raw
Or:
java Recover file://test.bin
The size of input will be a multiple of the block size. For each jpg image identified in the input file, write a separate file: image01.jpg, image02.jpg, image03.jpg, etc. Please use names of this form; start numbering with 1; use 2 decimal digits. Do not output thumbnail images (or any other suparts) separately.
