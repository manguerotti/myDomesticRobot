// Agent myRobotDel in project DomesticRobot.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!sendPrices(beer, mahou).
!sendPrices(beer, heineken).
!sendPrices(beer, estrella).
!sendPrices(pincho, tortilla).
!sendPrices(pincho, empanada).
!pillarDatos.


/* Plans */
tamPackBeer(10).
wallet(1000).

+!buy(beer, Brand): wallet(M) & tamPackBeer(Qtd)<-
	!comparePrices(beer,Brand);
	?cheaper(Super, beer, Brand, Stock, Precio);
		if(Qtd <= Stock){
			//Escenario 1
			!order(Super,beer,Brand, Qtd);
		}else{
			//Escenario 2, caso B
			Resto = Qtd - Stock;
			?barata(Super, beer, BrandB, StockB, PrecioB);
			if(Stock > 0){
				!orderDosTipos(Super, beer, Brand, Stock, BrandB, Resto);
			}else{
			//Escenario 3
				?masbarataAll(SuperC, beer, BrandC, StockC, PrecioC);
				if(StockC >= Qtd){
					!order(SuperC, beer, BrandC);
				}else{
					.println("No he podido comprar más Cervezas, no tienen Stock Suficiente");
				}
			}
		}.
	
+!buy(pincho, Brand)<-
	.wait(100);
	!comparePrices(pincho,Brand);
	?cheaper(S,pincho,Brand,Stock,_);
	!order(S,pincho,Brand,1).
	



+!notEnoughM(Qtd, Price)[source(Ag)]<-
	.send(myOwner,achieve,moneyReq(Qtd, Price)).

+!send_money(OwnerM)[source(Ag)]<-
	-wallet(M);
	+wallet(M+OwnerM).

+!orderDosTipos(Super, beer, Brand, Qtd, BrandB, Resto)<-
	.println("El robot ha realizado un pedido múltiple al supermercado.");
	!go_at(myRobotDel,delivery);
	.println("El robot va a la ZONA de ENTREGA.");
	?wallet(M);
	.send(Supermarket, achieve, orderMulti(beer,Brand, Qtd ,BrandB, Resto,M ));
	+ordered(beer).

	
	
+!order(Supermarket,pincho,Brand,Qtd) : not ordered(pincho) & not deliveredP & not deliveredB<-
	.println("El robot ha realizado un pedido al supermercado.");
	!go_at(myRobotDel,delivery);
	.println("El robot va a la ZONA de ENTREGA.");
	?wallet(M);
	.send(Supermarket, achieve, order(pincho,Brand,Qtd,M));
	+ordered(pincho).

+!order(Supermarket,Product,Brand,Qtd) : not ordered(Product) & not deliveredP & not deliveredB<-
	.println("El robot ha realizado un pedido al supermercado.");
	!go_at(myRobotDel,delivery);
	.println("El robot va a la ZONA de ENTREGA.");
	?wallet(M);
	.send(Supermarket, achieve, order(Product,Brand,Qtd,M));
	+ordered(Product).

+!order(Supermarket,Product,Brand,Qtd)<-
	!order(Supermarket,Product,Brand,Qtd).


	
/*+!updatePrices(Product,Brand)<-
	.abolish(s1price(_,_,_,_,_));
	.abolish(s2price(_,_,_,_,_));
	.abolish(s3price(_,_,_,_,_));
	.send(mySupermarket,achieve,send_price(Product, Brand));
	.send(mySupermarket2,achieve,send_price(Product, Brand));
	.send(mySupermarket3,achieve,send_price(Product, Brand)).*/
	
+!sendPrices(Product, Brand)<-
	.send(mySupermarket,achieve,send_price(Product, Brand));
	.send(mySupermarket2,achieve,send_price(Product, Brand));
	.send(mySupermarket3,achieve,send_price(Product, Brand)).
	
+!comparePrices(Product,Brand)<-
		.wait(100);
		?sprice(mySupermarket,Product,Brand,St1,P1);
		?sprice(mySupermarket2,Product,Brand,St2,P2);
		?sprice(mySupermarket3,Product,Brand,St3,P3);
		if(P1 <= P2 & P1 <= P3) {
			+cheaper(mySupermarket,Product,Brand,St1,P1);
		}
		elif (P2 <= P1 & P2 <= P3) {
			+cheaper(mySupermarket2,Product,Brand,St2,P2);
		}
		elif (P3 <= P1 & P3 <= P2) {
			+cheaper(mySupermarket3,Product,Brand,St3,P3);
		}.
		
+!pillarDatos<-
	.wait(500);
	!cheapestBeer(mySupermarket);
	!cheapestBeer(mySupermarket2);
	!cheapestBeer(mySupermarket3);
	!cheapestBeerAll.

+!cheapestBeer(S)<-
	.findall(P,sprice(S,beer,_,_,P),L);
	.min(L,PrecioBarato);
	?sprice(S,beer,Brand,Stock,PrecioBarato);
	+barata(S, beer, Brand, Stock, PrecioBarato).

+!cheapestBeerAll<-
	.findall(P,sprice(_,beer,_,_,P),L);
	.min(L,PrecioBarato);
	?sprice(S,beer,Brand,Stock,PrecioBarato);
	+masbarataAll(S, beer, Brand, Stock, PrecioBarato).
	
	
		
+!delivered(pincho,Brand,Qtd,OrderId, Cost) <-
	-wallet(M);
	+wallet(M-Cost);
	?wallet(X);
	.println(" ahora me queda: ", X);
	-ordered(pincho);
	.println("Vamos a coger ",Qtd*5,  " platos para hacer ",Qtd*5," pinchos");
	!go_at(myRobotDel,cboard);
	takeDishes(dishes, Qtd*5);
	.println("Vamos a cortar los/las ",Qtd," ",Brand," para hacer 5 pinchos con cada uno/a");
	!go_at(myRobotDel,fridge);
	deliverP(pincho, Qtd*5);
	.send(myFridge, achieve, addStock(pincho, Brand, Qtd*5));
	//.wait(20);
	!go_at(myRobotDel,base);
	.abolish(deliveredP).
		
+!delivered(Product,Brand,Qtd,OrderId, Cost) <-
	-wallet(M);
	+wallet(M-Cost);
	?wallet(X);
	.println(" ahora me queda: ", X);
	-ordered(Product);
	.send(myFridge, achieve, addStock(Product, Brand, Qtd));
	.abolish(deliveredB);
	.wait(100).
	
+!deliveredDos(Product,Brand,Qtd,BrandB,QtdB,OrderId, Cost) <-
	-wallet(M);
	+wallet(M-Cost);
	?wallet(X);
	.println(" ahora me queda: ", X);
	-ordered(Product);
	.send(myFridge, achieve, addStock(Product, Brand, Qtd));
	.send(myFridge, achieve, addStock(Product, BrandB, QtdB));
	.abolish(deliveredB);
	.wait(100).


	

+!go_at(myRobotDel,P) : at(myRobotDel,P) <- true.
+!go_at(myRobotDel,P) : not at(myRobotDel,P)
  <- move_towards(P);
     !go_at(myRobotDel,P).
	

