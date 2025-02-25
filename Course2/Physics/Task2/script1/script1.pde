
title 'Birthday'
variables T
equations div(-grad(T))=0
boundaries region 1 start(0,0) value(T)=x line to (18, 0) value(T)=18 line to (18,9) value(T)=x*x/18 line to (0,9) value(T)=0 line to close
plots contour(T) 
vector(-grad(T)) end