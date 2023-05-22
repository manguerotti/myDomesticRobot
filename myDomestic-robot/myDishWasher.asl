// Agent myDishWasher in project DomesticRobot.mas2j

/* Initial beliefs and rules */

/* Initial goals */

dishes(0).
!washDishes.

/* Plans */

+!addDish<-
	?dishes(N);
	-dishes(N);
	addDish(dish);
	+dishes(N+1).
	
+!washDishes: dishes(S) & S == 3<-
	-dishes(S);
	+dishes(0);
	.println("La lavadora está llena. Vamos a ponerla a funcionar.");
	.wait(100);
	.println("Platos lavados!!");
	.send(myRobotDWash, tell, finishDWash);
	!washDishes.
+!washDishes<-!washDishes.

