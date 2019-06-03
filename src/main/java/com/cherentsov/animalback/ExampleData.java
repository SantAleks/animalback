package com.cherentsov.animalback;

import com.cherentsov.animalback.Model.*;
import com.cherentsov.animalback.Service.DBService;
import org.hibernate.HibernateException;

public class ExampleData {
    //Наполнение БД тестовыми данными
    static void AddExampleToDB() {
        Region novosibirsk = new Region((long) -1, "Новосибирск");
        Region omsk = new Region((long) -1, "Омск");
        Region tomsk = new Region((long) -1, "Томск");
        Region kemerovo = new Region((long) -1, "Кемерово");
        Region novokuzneck = new Region((long) -1, "Новокузнецк");

        Location sovhozPobeda = new Location((long) -1, "Совхоз \"Победа\"", omsk);
        Location ermolinckajPticefabrika = new Location((long) -1, "Ермолинская птицефабрика", novosibirsk);
        Location tankistov15 = new Location((long) -1, "ул. Танкистов, д.15", novosibirsk);
        Location zoopark = new Location((long) -1, "Зоопарк", tomsk);
        Location lenina14 = new Location((long) -1, "ул. Ленина, д. 14, кв. 36", kemerovo);
        Location fermerVolkov = new Location((long) -1, "Фермерское хозяйство Волкова", novokuzneck);

        SkinColor grey = new SkinColor((long) -1,"Серый");
        SkinColor white = new SkinColor((long) -1,"Белый");
        SkinColor black = new SkinColor((long) -1,"Чёрный");
        SkinColor green = new SkinColor((long) -1,"Зелёный");
        SkinColor red = new SkinColor((long) -1,"Рыжий");

        AnimalType cow = new AnimalType((long) -1,"Корова");
        AnimalType duck = new AnimalType((long) -1,"Утка");
        AnimalType chicken = new AnimalType((long) -1,"Курица");
        AnimalType goat = new AnimalType((long) -1,"Коза");
        AnimalType dog = new AnimalType((long) -1,"Собака");
        AnimalType cat = new AnimalType((long) -1,"Кошка");
        AnimalType lizard = new AnimalType((long) -1,"Ящерица");
        AnimalType pig = new AnimalType((long) -1,"Свинья");

        Pet number154 = new Pet((long) -1,"Инвентарный номер 154",sovhozPobeda, cow, white);
        Pet number131 = new Pet((long) -1,"Инвентарный номер 131",sovhozPobeda, cow, grey);
        Pet number8649 = new Pet((long) -1,"Инвентарный номер 8649",ermolinckajPticefabrika, duck, black);
        Pet number45657 = new Pet((long) -1,"Инвентарный номер 45657",ermolinckajPticefabrika, chicken, red);
        Pet solnjshko = new Pet((long) -1,"Солнышко",tankistov15, cow, red);
        Pet polkan = new Pet((long) -1,"Полкан",tankistov15, dog, black);
        Pet piatnjshko = new Pet((long) -1,"Пятнышко",tankistov15, pig, grey);
        Pet spokojnaia = new Pet((long) -1,"Спокойная",zoopark, lizard, green);
        Pet pirat = new Pet((long) -1,"Пират",lenina14, cat, red);
        Pet number167 = new Pet((long) -1,"Инвентарный номер 167",fermerVolkov, goat, grey);
        Pet number984 = new Pet((long) -1,"Инвентарный номер 984",fermerVolkov, pig, black);

        DBService dBService = DBService.getInstance();
        try {
            //Регионы
            novosibirsk.setId(dBService.create(novosibirsk));
            omsk.setId(dBService.create(omsk));
            tomsk.setId(dBService.create(tomsk));
            kemerovo.setId(dBService.create(kemerovo));
            novokuzneck.setId(dBService.create(novokuzneck));
            //Местоположения
            sovhozPobeda.setId(dBService.create(sovhozPobeda));
            ermolinckajPticefabrika.setId(dBService.create(ermolinckajPticefabrika));
            tankistov15.setId(dBService.create(tankistov15));
            zoopark.setId(dBService.create(zoopark));
            lenina14.setId(dBService.create(lenina14));
            fermerVolkov.setId(dBService.create(fermerVolkov));
            //Цвет шкуры
            grey.setId(dBService.create(grey));
            white.setId(dBService.create(white));
            black.setId(dBService.create(black));
            green.setId(dBService.create(green));
            red.setId(dBService.create(red));
            //Вид животного
            cow.setId(dBService.create(cow));
            duck.setId(dBService.create(duck));
            chicken.setId(dBService.create(chicken));
            goat.setId(dBService.create(goat));
            dog.setId(dBService.create(dog));
            cat.setId(dBService.create(cat));
            lizard.setId(dBService.create(lizard));
            pig.setId(dBService.create(pig));
            //Питомец
            dBService.create(number154);
            dBService.create(number131);
            dBService.create(number8649);
            dBService.create(number45657);
            dBService.create(solnjshko);
            dBService.create(polkan);
            dBService.create(piatnjshko);
            dBService.create(spokojnaia);
            dBService.create(pirat);
            dBService.create(number167);
            dBService.create(number984);
        } catch (
                HibernateException e) {
            e.printStackTrace();
        }

    }
}
