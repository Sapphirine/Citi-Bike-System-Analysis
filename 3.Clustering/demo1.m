clc ;
x=[startstationlongitude startstationlatitude];
idx=kmeans(x,5);
plot(startstationlongitude, startstationlatitude,'bo');
pause;
hold on
for i=1:5
    cx=startstationlongitude.*[idx==i];
    cy=startstationlatitude.*[idx==i];
    cx=removeZero(cx);
    cy=removeZero(cy);
    plot(cx,cy,'r*');
    pause
end

figure(2)
plot(startstationlongitude, startstationlatitude,'bo')
hold on

c1x=startstationlongitude.*[idx==1];
c1y=startstationlatitude.*[idx==1];
c1x=removeZero(c1x);
c1y=removeZero(c1y);
plot(c1x,c1y,'r*')


c2x=startstationlongitude.*[idx==2];
c2y=startstationlatitude.*[idx==2];
c2x=removeZero(c2x);
c2y=removeZero(c2y);
plot(c2x,c2y,'g*')

c3x=startstationlongitude.*[idx==3];
c3y=startstationlatitude.*[idx==3];
c3x=removeZero(c3x);
c3y=removeZero(c3y);
plot(c3x,c3y,'c*')

c4x=startstationlongitude.*[idx==4];
c4y=startstationlatitude.*[idx==4];
c4x=removeZero(c4x);
c4y=removeZero(c4y);
plot(c4x,c4y,'m*')

c5x=startstationlongitude.*[idx==5];
c5y=startstationlatitude.*[idx==5];
c5x=removeZero(c5x);
c5y=removeZero(c5y);
plot(c5x,c5y,'k*')


