'''
DATA GENERATOR FOR RECREATION CENTRE APP
'''

#CONSTANTS

BOOKED_SQUASH_COURT = 0
BOOKED_RACQUET_COURT = 1
USER_SQUASH_COURT = 2
USER_RACQUET_COURT = 3
AVAILABLE_SQUASH_COURT = 4
AVAILABLE_RACQUET_COURT = 5

import random

#SETUP

startMonth = int(input("Enter start month: "))
endMonth = int(input("Enter end month: "))
startDay = int(input("Enter start day: "))
endDay = int(input("Enter end day: "))
startYear = int(input("Enter start year: "))
endYear = int(input("Enter end year: "))
startHour = int(input("Enter start time (in 24hr): "))
endHour = int(input("Enter end time (in 24hr): "))
user = input("Enter name of user: ") + "'s"
bsc = input("Enter number of booked squash courts (leave blank for random): ")
brc = input("Enter number of booked racquet courts (leave blank for random): ")
asc = input("Enter number of available squash courts (leave blank for random): ")
arc = input("Enter number of available racquet courts (leave blank for random): ")
usc = input("Enter number of user squash courts (leave blank for random): ")
urc = input("Enter number of user squash courts (leave blank for random): ")

# SETUP 2
vals = []

# BSC
if (bsc == ""):
	bsc = random.randint(0, 100)
else:
	bsc = int(bsc)
vals.append(bsc)

#BRC
if (brc == ""):
	brc = random.randint(0, 100)
else:
	brc = int(brc)
vals.append(brc)

#USC
if (usc == ""):
	usc = random.randint(0, 100)
else:
	usc = int(usc)
vals.append(usc)

#URC
if (urc == ""):
	urc = random.randint(0, 100)
else:
	urc = int(urc)
vals.append(urc)

#ASC
if (asc == ""):
	asc = random.randint(0, 100)
else:
	asc = int(asc)
vals.append(asc)

#ARC
if (arc == ""):
	arc = random.randint(0, 100)
else:
	arc = int(arc)
vals.append(arc)

#COUNT ENTRIES
totalEntries = 0
for i in vals:
	totalEntries = totalEntries + i	

#GENERATION

f = open("gendata", "w")

for idt in range(totalEntries):
	
	notDone = True
	while (notDone):
		t = random.randint(0, 5)
		if (vals[t] != 0):
			notDone = False
			vals[t] = vals[t] - 1

	text = ""

	if (t == AVAILABLE_RACQUET_COURT):
		text = "Available Racquet Court"
	elif (t == AVAILABLE_SQUASH_COURT):
		text = "Available Squash Court"
	elif (t == BOOKED_RACQUET_COURT):
		text = "Booked Racquet Court"
	elif (t == BOOKED_SQUASH_COURT):
		text = "Booked Squash Court"
	elif (t == USER_RACQUET_COURT):
		text = user + " Racquet Court"
	elif (t == USER_SQUASH_COURT):
		text = user + " Squash Court"

	genMonth = random.randint(startMonth, endMonth)
	genDay = random.randint(startDay, endDay)
	genYear = random.randint(startYear, endYear)
	gensh = random.randint(startHour, endHour)
	geneh = gensh + random.choice([1,2])

	outStuff = str(idt) + '\t' +  text + '\t' + str(genMonth) + '\t' + str(genDay) + '\t' + str(genYear) + '\t' +  str(gensh) + '\t' + "00" + '\t' + str(genMonth) + '\t' + str(genDay) + '\t' + str(genYear) + '\t' + str(geneh) + '\t' + "00" + '\t' + str(t) + '\n'
	f.write(outStuff)
	#print(idt, '\t',  text, '\t', genMonth, '\t', genDay, '\t', genYear, '\t',  gensh, '\t', "00", '\t', genMonth, '\t', genDay, '\t', genYear, '\t', geneh, '\t', "00", '\t', t )

f.close()  # you can omit in most cases as the destructor will call it