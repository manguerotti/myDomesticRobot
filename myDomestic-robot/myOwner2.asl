/* Initial beliefs and rules */

/* Initial goals */

!drink(beer).
!check_bored.
!cleanBottle.

beer_brand(mahou).
pinchoType(tortilla).
id(2).
wallet(math.round(math.random(500))+500).

/* Plans */

// if I have not beer finish, in other case while I have beer, sip



+!drink(beer) : ~couldDrink(beer) <-
	.println("Owner ha bebido demasiado por hoy.").	
+!drink(beer) : has(myOwner2,beer) & has(myOwner2,pincho) & asked(beer) <-
	.println("Owner va a empezar a beber cerveza y a comer el pincho");
	-asked(beer);
	sip(beer);
	eat(pincho);
	!drink(beer).
+!drink(beer) : has(myOwner2,beer) & has(myOwner2,pincho) & not thrown(garb2)& not asked(beer) <-
	sip(beer);
	.println("Owner está bebiendo cerveza.");
	eat(pincho);
	.println("Owner está comiendo el pincho.");
	throwBottle(garb);
	throwPincho(dish);
	!drink(beer).
+!drink(beer) : not has(myOwner,beer) & not thrown(garb1) & not asked(beer) <-
	?id(Id);
	.println("Owner no tiene cerveza.");
	.random(N);
	if(N > 0.1){
		!get(beer);
	}else{
		.send(myRobot, askOne, turno(T), turno(T));
		if(T == Id){
			.send(myRobot, achieve, changeTurn(T));
		}
		!pickFood;
	}
	!drink(beer).
+!drink(beer) : not has(myOwner2,beer) & not pickingBottle & not thrown(garb2) & asked(beer) <- 
	.println("Owner está esperando una cerveza.");
	.wait(5000);
	!drink(beer).
+!drink(beer)<-!drink(beer).

+!pickFood: not thrown(garb2) & not pickingBottle<-
	.println("Voy a ir a la nevera yo mismo");
	!go_at(myOwner2,fridge);
	.println("Voy a abrir la nevera");
	open(fridge);
	get(beer2);
	get(pincho2);
	.send(myFridge, achieve, getOne(beer,Brand));
	.send(myFridge, achieve, getOne(pincho,Pincho));
	.println("Voy a cerrar la nevera");
	close(fridge);
	!go_at(myOwner2,base);
	hand_in(beer2);
	hand_in(pincho2).
	//?has(myOwner2,beer);
	//?has(myOwner2,pincho).
+!pickFood<-
	.println("").
	
	
+!cleanBottle : thrown(garb2)<-
	+pickingBottle;
	.println("Voy yo mismo a recoger mi botella");
	!go_at(myOwner2,garb2);
	pickUpBottle(garb2);
	!go_at(myOwner2,paper);
	thrashBottle(garb);
	.wait(100);
	.println("Botella echada en la basura");
	.abolish(thrown(garb2));
	!go_at(myOwner2,base);
	.abolish(pickingBottle);
	!cleanBottle.
+!cleanBottle<-!cleanBottle.

+!get(beer) : not asked(beer) <-
	?id(Id);
	?wallet(M);
	?beer_brand(Brand);
	?pinchoType(Pincho);
	.send(myRobot, achieve, bring(Id,beer, Brand, Pincho));
	.send(myRobotDel, achieve, send_money(50));
	-wallet(M);
	+wallet(M-50);
	.println("Owner ha pedido una cerveza al robot acompañado de un pincho.");
	+asked(beer).

+!moneyReq(Qtd, Price)[source(Ag)]<-
	.println("Enviando dinero a ",Ag," ...",Qtd*Price*5);
	.send(Ag, achieve, send_money(Qtd*Price*5)).
	
+!check_bored : true
   <- .random(X); .wait(X*5000+2000);  // i get bored at random times
      .send(myRobot, askOne, time(_), R); // when bored, I ask the robot about the time
      .print(R);
      !check_bored.
	  
	  
+!go_at(myOwner2,P) : at(myOwner2,P) <- true.
+!go_at(myOwner2,P) : not at(myOwner2,P)
  <- move_towards(P);
     !go_at(myOwner2,P).
	
	
+msg(M)[source(Ag)] <- 
	.print("Message from ",Ag,": ",M);
	+~couldDrink(beer);
	-msg(M).



