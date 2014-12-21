function y=removeZero(a)
    index=find(a==0);
    a(index)=[];
    y=a;
end