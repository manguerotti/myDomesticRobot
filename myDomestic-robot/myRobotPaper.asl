// Agent myRobotPaper in project DomesticRobot.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!emptyPaper.

/* Plans */

+!emptyPaper : paperFull <-
	.print("La basura está llena, voy a quemar la basura");
	burning(garb);
	.wait(3000);
	emptyPaper;
	burning(garb);
	.print("Ya he quemado la basura");
	!emptyPaper.
+!emptyPaper<-!emptyPaper.
