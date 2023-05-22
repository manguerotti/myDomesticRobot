// Agent myRobotDWash in project DomesticRobot.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!cleanDish.


/* Plans */

+!cleanDish: finishDWash<-
	.abolish(finishDWash);
	.println("Voy a recoger los platos lavados y llevarlos a la alacena");
	!go_at(myRobotDWash,dwash);
	!go_at(myRobotDWash,cboard);
	emptyDW(dishes);
	.println("Voy a dejarlos en alacena.");
	addCBoard(dishes);
	!go_at(myRobotDWash,base);
	!cleanDish.
+!cleanDish: thrown(dish) & not finishDWash<-
	.println("Voy a recoger el plato vacío");
	.wait(100);
	!go_at(myRobotDWash,myOwner);
	pickUpDish(dish);
	!go_at(myRobotDWash,dwash);
	.send(myDishWasher,achieve,addDish);
	.abolish(thrown(dish));
	.wait(100);
	!go_at(myRobotDWash,base);
	!cleanDish.

+!cleanDish: thrown(dish2) & not finishDWash <-
	.println("Voy a recoger el plato vacío");
	.wait(100);
	!go_at(myRobotDWash,myOwner2);
	pickUpDish(dish2);
	!go_at(myRobotDWash,dwash);
	.abolish(thrown(dish2));
	.wait(100);
	.send(myDishWasher,achieve,addDish);
	!go_at(myRobotDWash,base);
	!cleanDish.
	
+!cleanDish: not thrown(dish)<-
	.wait(2000);
	.println("Esperando por el plato");
	!cleanDish.
	




+!go_at(myRobotDWash,P) : at(myRobotDWash,P) <- true.
+!go_at(myRobotDWash,P) : not at(myRobotDWash,P)
  <- move_towards(P);
     !go_at(myRobotDWash,P).
