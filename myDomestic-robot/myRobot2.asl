/* Initial beliefs and rules */

// initially, I believe that there is some beer in the fridge
available(beer,fridge).

// my owner should not consume more than 10 beers a day :-)
limit(beer,5).

wallet(1000).

too_much(B) :-
   .date(YY,MM,DD) &
   .count(consumed(YY,MM,DD,_,_,_,B),QtdB) &
   limit(B,Limit) &
   QtdB > Limit.

/* Initial goals */

!bringBeer.

/* Plans */

	 

+!bring(myOwner2, P, Brand, Pincho) <-
	+asked(P, Brand, Pincho).
	
+!bringBeer : healthMsg(_) <- 
	!go_at(myRobot2,base);
	.println("El Robot descansa porque Owner ha bebido mucho hoy.").

/*+!bringBeer : asked(beer, Brand, Pincho) & not healthMsg(_) & thrown(garb)<-
	.println("pick bottle");
	!go_at(myRobot2,garb);
	pickUpBottle(garb);
	!go_at(myRobot2,paper);
	thrashBottle(garb);
	.wait(100);
	.println("Botella echada en la basura");
	-thrown(garb);
	!bringBeer.*/
+!bringBeer : asked(beer, Brand, Pincho) & not healthMsg(_)<-
	.println("Owner me ha pedido una cerveza acompañado de un pincho.");
	!go_at(myRobot2,fridge);
	!take(fridge,beer, Brand, Pincho);
	!go_at(myRobot2,myOwner2);
	!hasBeer(myOwner2);
	.println("Ya he servido la cerveza y el pincho y elimino la petición.");
	.abolish(asked(Beer, Brand, Pincho));
	!bringBeer.
+!bringBeer : not asked(beer, Brand) & not healthMsg(_) <- 
	.wait(2000);
	.println("Robot esperando la petición de Owner.");
	!bringBeer.

+!take(fridge, P, Brand, Pincho) : not too_much(beer) <-
	.println("El robot está cogiendo una cerveza y un pincho para acompañar.");
	.send(myFridge, askOne, stock(P, Brand, S), stock(P, Brand, S));
	.send(myFridge, askOne, stock(pincho, Pincho, Sp), stock(pincho, Picnho, Sp));
	if(S == 3){
		.send(myRobotDel, achieve, buy(P, Brand));
		.send(myRobotDel, achieve, buy(pincho, Pincho));
	};
	!check(fridge, P, Brand,Pincho).
+!take(fridge,beer) : too_much(beer) & limit(beer, L) <-
	.concat("The Department of Health does not allow me to give you more than ", L," beers a dftrahay! I am very sorry about that!", M);
	-+healthMsg(M).
	
		
	
+!check(fridge, P,Brand,Pincho)<-
	.println("El robot está en el frigorífico y coge una cerveza.");
	.wait(100);
	open(fridge);
	.println("El robot abre la nevera.");
	.wait(1000);
	get(beer);
	get(pincho);
	.send(myFridge, achieve, getOne(beer,Brand));
	.send(myFridge, achieve, getOne(pincho,Pincho));
	.println("El robot coge una cerveza y un pincho.");
	close(fridge);
	.println("El robot cierra la nevera.").


+!hasBeer(myOwner2) : not too_much(beer) <-
	hand_in(beer);
	hand_in(pincho);
	.println("He preguntado si Owner ha cogido la cerveza y el pincho.");
	?has(myOwner2,beer);
	?has(myOwner2,pincho);
	.println("Se que Owner tiene la cerveza.");
	// remember that another beer has been consumed
	.date(YY,MM,DD); .time(HH,NN,SS);
	+consumed(YY,MM,DD,HH,NN,SS,beer).
+!hasBeer(myOwner2) : too_much(beer) & healthMsg(M) <- 
	//.abolish(msg(_));
	.send(myOwner2,tell,msg(M)).

+!go_at(myRobot2,P) : at(myRobot2,P) <- true.
+!go_at(myRobot2,P) : not at(myRobot2,P)
  <- move_towards(P);
     !go_at(myRobot2,P).

	
+!clearPaper<-
	clearPaper(bottle);
	.send(myRobot2,tell,msg("botella recogida")).
	
+paper(N) : N > 2 <-
	.concat("La papelera tiene ", N," botellas ", Capacidad);
	.println(Capacidad);
	.send(myRobot2,tell,Capacidad).
	 


// when the supermarket makes a delivery, try the 'has' goal again




+?time(T) : true
  <-  time.check(T).



