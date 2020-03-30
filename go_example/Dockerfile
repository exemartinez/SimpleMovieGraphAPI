FROM golang:latest 
RUN mkdir /sparklygosimpleapi 
ADD . /sparklygosimpleapi/ 
WORKDIR /sparklygosimpleapi 
RUN go build -o main . 
CMD ["/sparklygosimpleapi/main"]