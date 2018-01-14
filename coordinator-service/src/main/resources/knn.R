library(class);
table = data.frame(rbind(
    c(23,8,30,32,4), 
    c(24,16,28,30,5),
    c(23,12,30,25,5),
    c(20,6,25,22,5),
    c(24,16,28,30,5),
    c(21,4,21,30,5)
))


colnames(table) = c('voto','hora', 'temp_e', 'temp_i', 'users')

data_norm = function(x) {((x-min(x))/(max(x)-min(x)))}
table_norm = as.data.frame(lapply(table[,-1], data_norm))
tes.train = table_norm[1:4,]
tes.test = table_norm[5:6,]

print(knn(tes.train, tes.test, table[1:4,1]))
