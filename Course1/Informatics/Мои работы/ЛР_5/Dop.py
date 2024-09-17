import plotly.graph_objs as draw
import pandas as pand

import csv

with open('Data.csv') as file:
    reader = csv.reader(file, delimiter=",", quotechar='"')
    next(reader, None)
    data_read = [row for row in reader]

candalOpen  = [[], [] ,[], []]
candalHigh  = [[], [] ,[], []]
candalLow   = [[], [] ,[], []]
candalClose = [[], [] ,[], []]

dates = { '17/09/18': 0, '17/10/18': 1, '19/11/18': 2, '17/12/18': 3 }
inv_dates = {v: k for k, v in dates.items()}

for raw in data_read:
    id = dates[raw[2]]
    candalOpen[id].append(float(raw[4]))
    candalHigh[id].append(float(raw[5]))
    candalLow[id].append(float(raw[6]))
    candalClose[id].append(float(raw[7]))

diagram = draw.Figure()
for i in range(4):
    cur_date = inv_dates[i]
    n = cur_date + ' - Open'
    diagram.add_trace(draw.Box(y=pand.DataFrame(candalOpen[i], columns=[n])[n]))

    n = cur_date + ' - High'
    diagram.add_trace(draw.Box(y=pand.DataFrame(candalHigh[i], columns=[n])[n]))

    n = cur_date + ' - Low'
    diagram.add_trace(draw.Box(y=pand.DataFrame(candalLow[i], columns=[n])[n]))

    n = cur_date + ' - Close'
    diagram.add_trace(draw.Box(y=pand.DataFrame(candalClose[i], columns=[n])[n]))

diagram.update_layout(legend=dict(yanchor="auto", orientation="h", y=5))
#diagram.update_xaxes(tickangle=90, title_standoff=25)
diagram.show()