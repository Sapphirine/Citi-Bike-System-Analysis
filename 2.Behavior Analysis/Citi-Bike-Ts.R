library(forecast)
library(tseries)
#input data
year<-read.delim("/Users/leilei/Desktop/year.csv",fileEncoding="UCS-2LE")
year<-year[,c(1,2)]
year[,2]<-as.numeric(year[,2])
#transform data
for(i in 1:nrow(year))
{
  data[i,]<-year[nrow(year)-i+1,]
}
train<-data[c(1:300),2]
test<-data[-c(1:300),2]

#arima
adf.test(data[,2])
#the data is not stationary.
#thus we take its first order difference
data_1<-diff(data[,2])
#do Adftest again 
adf.test(data_1)
#now it is stationary, thus we build an first order integreted arima model
citi<-arima(data[,2],order=c(1,1,1),seasonal=list(order=c(0,1,1)))
cpgram(citi$res,main='Cumulative Periodogram Plot for Residuals')
Box.test(citi$res)
tsdiag(citi)
predict(citi,30)

####adf test for test uni root
###arima model (p,i,q) p repsent auto regresssive integrated moving average
###diff first order first lag
###model meaning  and usage
##p order of autoregressve 



