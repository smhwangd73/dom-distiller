function Ovb(a){this.a=a}
function Lvb(a,b){this.a=a;this.b=b}
function PS(a,b){var c;c=hT((cT(),b));return QS(a,c,b,false)}
function Hvb(a){var b,c,d;b=oqc(hr(a.a.cb,vIc));c=oqc(hr(a.b.cb,vIc));d=oqc(hr(a.c.cb,vIc));k2b(a.f,zLc+b+ALc+c+BLc+d+zEc);p2b(a.d,zLc+PS(a.e,b)+ALc+_fb(c)+BLc+PS(a.e,d)+zEc)}
function Gvb(a){var b,c,d,e,f,g;d=new W4b;b=gU(d.j,100);d.o[GGc]=5;g=Igb(L1);e=new HZb(g);kj(e,new Lvb(a,g),(_w(),_w(),$w));f=new M6b;f.e[GGc]=3;J6b(f,new t2b("Cet exemple interagit avec l'interface \uFFFDchantillon:"));J6b(f,e);Q4b(d,0,0,f);d5b(b,0)[uIc]=2;N4b(d,1,0,'<b>Mod\uFFFDle de message:<\/b>');N4b(d,1,1,"Utilisateur {0} ({1}  postes) a ajout\xE9 un commentaire sur '{2}'");a.a=new tac;jac(a.a,(_E(),'\u05EA\u05D5\u05DE\u05E8 \u05D2\u05E8\u05D9\u05DF'));N4b(d,2,0,'<b>Argument {0}:<\/b>');Q4b(d,2,1,a.a);a.b=new tac;jac(a.b,'16');N4b(d,3,0,'<b>Argument {1}:<\/b>');Q4b(d,3,1,a.b);a.c=new tac;jac(a.c,'\u05DB\u05DE\u05D4 \u05D7\u05D5\u05DC \u05D9\u05E9 \u05D1\u05D7\u05D5\u05E3?');N4b(d,4,0,'<b>Argument {2}:<\/b>');Q4b(d,4,1,a.c);a.f=new r2b;N4b(d,5,0,'<b>Message mis en forme sans BidiFormatter:<\/b>');Q4b(d,5,1,a.f);i5b(b,5,0,(g6b(),f6b));a.d=new r2b;N4b(d,6,0,'<b>Message mis en forme avec BidiFormatter:<\/b>');Q4b(d,6,1,a.d);i5b(b,6,0,f6b);c=new Ovb(a);kj(a.a,c,(Lx(),Lx(),Kx));kj(a.b,c,Kx);kj(a.c,c,Kx);Hvb(a);return d}
var BLc="  postes) a ajout\xE9 un commentaire sur '",zLc='Utilisateur ';reb(649,1,IAc,Lvb);_.Dc=function Mvb(a){Bgb(this.a,this.b+CLc)};_.a=null;_.b=null;reb(650,1,tAc,Ovb);_.Fc=function Pvb(a){Hvb(this.a)};_.a=null;reb(651,1,LAc);_.lc=function Tvb(){_gb(this.b,Gvb(this.a))};var L1=cpc(DHc,'BlogMessages'),O1=apc(DHc,'CwBidiFormatting$1',649),P1=apc(DHc,'CwBidiFormatting$2',650);yBc(wn)(27);