#!/bin/bash

# 1
mkdir lab0
cd lab0
mkdir -p gabite4/treecko gabite4/rhyperior
touch gabite4/mienshao gabite4/toxicroak gabite4/cyndaquil
mkdir -p koffing4/duosion
cd koffing4
touch cranidos mantyke ivysaur bronzong seadra
cd ..
mkdir -p leavanny0/roggenrola leavanny0/fraxure
touch leavanny0/skorupi
echo -e "Ходы Bounce Drain Punch Dual Chop Helping Hand Knock Off\n Low Kick Role Play Sleep Talk Snore" > gabite4/mienshao
echo -e "Способности Venom\n Focus Dry Skin Anticipation" > gabite4/toxicroak
echo -e "Тип покемона FIRE\n NONE" > gabite4/cyndaquil
echo "Тип диеты Phototroph" > gloom0
echo -e "Живет Forest Glassland\nRainforest" > ivysaur8
echo -e "Способности Headbutt Leer Focus Energy Pursuit\nTake Down Scary Face Assurance Chip Away Ancientpower Zen Headbutt\n Screech Head Smash" > koffing4/cranidos
echo -e "Развитые способности Water\n Veil" > koffing4/mantyke
echo "Развитые способности Leaf Guard" > koffing4/ivysaur
echo -e "Тип покемона\n STEEL PSYCHIC" > koffing4/bronzong
echo -e "Ходы Bounce Dive Double-Edge Dragon Pulse Icy\nWind Outrage Signal Beam Sleep Talk Snore Swift Twister Water\nPulse" > koffing4/seadra
echo -e "Способности Venom Swarm Battle Armor\n Sniper" > leavanny0/skorupi
echo "Развитые способности Oblivious" > spheal9

# 2
chmod o+w-rx gabite4
chmod u+rx-w,g+x-rw,o+wx-r gabite4/treecko
chmod 571 gabite4/rhyperior
chmod 066 gabite4/mienshao
chmod 440 gabite4/toxicroak
chmod 404 gabite4/cyndaquil
chmod 622 gloom0
chmod 600 ivysaur8
chmod 311 koffing4
chmod 550 koffing4/duosion
chmod 404 koffing4/cranidos
chmod 444 koffing4/mantyke
chmod 046 koffing4/ivysaur
chmod 404 koffing4/bronzong
chmod 060 koffing4/seadra
chmod 513 leavanny0
chmod 736 leavanny0/roggenrola
chmod 444 leavanny0/skorupi
chmod 737 leavanny0/fraxure
chmod 640 spheal9
cd

# 3
ln -s /home/studs/s408409/lab0/ivysaur8 lab0/koffing4/mantykeivysaur
cp lab0/spheal9 lab0/gabite4/cyndaquilspheal
ln lab0/gloom0 lab0/koffing4/mantykegloom
chmod u+r lab0/koffing4
chmod u+r lab0/koffing4/ivysaur
chmod u+r lab0/koffing4/seadra
cp -R lab0/koffing4 lab0/leavanny0/fraxure
ln -s /home/studs/s408409/lab0/leavanny0 lab0/Copy_17
cp lab0/ivysaur8 lab0/leavanny0/fraxure
cat lab0/koffing4/mantyke lab0/koffing4/mantyke > lab0/gloom0_83
chmod u-r lab0/koffing4
chmod u-r lab0/koffing4/ivysaur
chmod u-r lab0/koffing4/seadra

#4
#4.1
cd lab0
wc -l ivysaur8 > /tmp/tmp0
#4.2
ls -lRr | grep "r$"
#4.3
cat leavanny0/* 2>/dev/null | grep -isR 'Ven'
#4.4
cat -n gabite4/* 2>1 | grep -i "un"
#4.5
ls -lRSr | grep ro | tail -4
#4.6
cat -n gabite4/* koffing4/* 1 | grep Fo

# 5
#5.1
rm spheal9
#5.2
chmod u+w koffing4/seadra
rm koffing4/seadra
#5.3
rm Copy_*
#5.4
chmod u+w koffing4
rm koffing4/mantykeglo*
chmod u-w koffing4
#5.6
rmdir leavanny0/roggenrola
#5.5
chmod -R u+rwx leavanny0
rm -r leavanny0