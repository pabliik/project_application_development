so what i did is I calculated how much each wolf needs
one wolfs eat 5 kg per day
so 1825 kg per year
then i divide 1825 over each herbivore mass

cattle = 1825/750 = 2.43 cattle per year
horse = 1825/450 = 4.05 horse per year
deer = 1825/240 = 7.6 deer per year

and then i divide each value with its popualtion to get the hunt rate
cattle hunt rate = 2.43 / 375 = 0.00648
horse hunt rate = 4.05 / 350 = 0.01157
deer hunt rate = 7.6 / 870 = 0.00874

these numbers would be consant 

then kills per year would be
killsCattle = cattle.getHuntRate() * currentCattle * currentWolves
killsHorse = horse.getHuntRate() * currentHorse * currentWolves
killsDeer = deer.getHuntRate() * currentDeer * currentWolves

and then for predator population growth i pass these kills to the predator population model and multiply with conversion efficiency and 0.5 which means there are 50% of female wolves to get the new population of wolves