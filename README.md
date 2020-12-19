# Group2_Weather
Build a console application that takes a city and state name as input
The city and state are posted to a [queue X]
A backend service will read the city and state information and pass them on to https://openweathermap.org/current and fetch the current temperature
The current temperature along with the city/state is posted to another [queue Y]
Build another console application that polls for the temperature from [queue Y] and prints it on the console
