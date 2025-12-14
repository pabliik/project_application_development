def simulateModel(animal, carrying_capacity, steps):
        
    if steps == 0:
        return animal
    
    for i in range(steps):
        animal += 1
        simulateModel(animal, carrying_capacity, steps - 1)