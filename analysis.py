import csv
data={}#hold avergage of the winning for each count
#key: count, value: average
with open('game.csv', mode='r') as infile:
    reader = csv.reader(infile)
    #skip the header
    next(reader)
    for row in reader:
        count=float(row[0])
        win=float(row[1])
        #update the avgerage if the key is already in the dictionary else add the key and value
        data.update({count: (data[count]+win)/2 if count in data else win})
#sort the dictionary by key and print the result
#create csv file
with open('analysis.csv', mode='w') as outfile:
    writer = csv.writer(outfile)
    writer.writerow(['Count', 'Average'])
    for key in sorted(data.keys()):
        writer.writerow([key, data[key]])
        