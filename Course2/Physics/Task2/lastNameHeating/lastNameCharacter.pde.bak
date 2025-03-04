TITLE 'Simple diffusion equation' { this problem lacks sources and boundary conditions } 
VARIABLES T 
EQUATIONS div(-grad(T)) = 100*exp( -(x-0.75)^2 - (y-1.75)^2)
BOUNDARIES REGION 1 
start(0,0)
  line to (0,3)   natural (T)=0
  line to (2,3)   natural (T)=0
  line to (2,2)   natural (T)=0
  line to (1,2)   natural (T)=0
  line to (1,0)   natural (T)=0
  line to (0,0)   natural (T)=0
PLOTS CONTOUR(T) 
PLOTS CONTOUR(100*exp( -(x-0.75)^2 - (y-1.75)^2)) 
VECTOR(-grad(T)) END