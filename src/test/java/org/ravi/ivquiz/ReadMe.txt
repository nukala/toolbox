Vehicles interview quiz class from:
http://www.javamagazine.mozaicreader.com/MarchApril2019/Facebook#&pageSet=81&page=0

Questions:
 what gets printed ?
   Answer = "Combustion";
 Why ?
   compiler method selection rules goes from specific to generic. Since Jet is not
   found, it use Combustion setter
   Then each class has its own member with its own value. Since we are using combustion
   setter, its value will be used
 How to fix?
   make type final and add more constructors.