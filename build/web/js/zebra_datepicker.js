(function(a){
    a.Zebra_DatePicker=function(r,A){
        var D={
            days:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],
            direction:0,
            disabled_dates:false,
            first_day_of_week:1,
            format:"Y-m-d",
            months:["January","February","March","April","May","June","July","August","September","October","November","December"],
            offset:[20,-5],
            readonly_element:true,
            view:"days",
            weekend_days:[0,6]
            };
            
        var c,C,p,f,G,b,l,j,u,g,s,t,I,k,x,z,K,L,o,H,n;
        var M=this;
        M.settings={};
        
        var e=a(r);
        M.init=function(){
            M.settings=a.extend({},D,A);
            if(M.settings.readonly_element){
                e.attr("readonly","readonly")
                }
                c=M.settings.view;
            var Q='<button type="button" class="Zebra_DatePicker_Icon">Pick a date</button>';
            p=a(Q);
            n=(M.settings.direction===true||m(M.settings.direction)>0)?true:((M.settings.direction===false||m(M.settings.direction)<0)?false:0);
            var P=new Date();
            s=P.getMonth();
            j=P.getMonth();
            t=P.getFullYear();
            u=P.getFullYear();
            I=P.getDate();
            g=P.getDate();
            if(n!==0){
                P=new Date(t,s,I+m(M.settings.direction));
                s=P.getMonth();
                t=P.getFullYear();
                I=P.getDate()
                }
                if(J(O(t,q(s,2),q(I,2)))){
                while(J(t)){
                    if(!n){
                        t--
                    }else{
                        t++
                    }
                    s=0
                    }while(J(O(t,q(s,2)))){
                    if(!n){
                        s--
                    }else{
                        s++
                    }
                    if(s>11){
                        t++;
                        s=0
                        }else{
                        if(s<0){
                            t--;
                            s=0
                            }
                        }
                    I=1
                }while(J(O(t,q(s,2),q(I,2)))){
                if(!n){
                    I--
                }else{
                    I++
                }
                P=new Date(t,s,I);
                t=P.getFullYear();
                s=P.getMonth();
                I=P.getDate()
                }
            }
        p.bind("click",function(S){
        S.preventDefault();
        if(C.css("display")!="none"){
            M.hide()
            }else{
            var R=h(e.val());
            if(R){
                K=R.getMonth();
                k=R.getMonth();
                L=R.getFullYear();
                x=R.getFullYear();
                z=R.getDate();
                if(J(O(L,q(K,2),q(z,2)))){
                    k=s;
                    x=t
                    }
                }else{
            k=s;
            x=t
            }
            F();
        M.show()
        }
    });
p.insertAfter(r);
    var Q='<div class="Zebra_DatePicker"><table class="dp_header"><tr><td class="dp_previous">&laquo;</td><td class="dp_caption">&nbsp;</td><td class="dp_next">&raquo;</td></tr></table><table class="dp_daypicker"></table><table class="dp_monthpicker"></table><table class="dp_yearpicker"></table></div>';
    C=a(Q);
    f=C.find("table.dp_header").first();
    G=C.find("table.dp_daypicker").first();
    b=C.find("table.dp_monthpicker").first();
    l=C.find("table.dp_yearpicker").first();
    a("body").append(C);
    C.delegate("td:not(.dp_disabled, .dp_weekend_disabled, .dp_not_in_month, .dp_blocked)",{
    mouseover:function(){
        a(this).addClass("dp_hover")
        },
    mouseout:function(){
        a(this).removeClass("dp_hover")
        }
    });
i(f.find("td"));
f.find(".dp_previous").bind("click",function(){
    if(!a(this).hasClass("dp_blocked")){
        if(c=="months"){
            x--
        }else{
            if(c=="years"){
                x-=12
                }else{
                if(--k<0){
                    k=11;
                    x--
                }
            }
        }
    F()
    }
});
f.find(".dp_caption").bind("click",function(){
    if(c=="days"){
        c="months"
        }else{
        if(c=="months"){
            c="years"
            }else{
            c="days"
            }
        }
    F()
    });
f.find(".dp_next").bind("click",function(){
    if(!a(this).hasClass("dp_blocked")){
        if(c=="months"){
            x++
        }else{
            if(c=="years"){
                x+=12
                }else{
                if(++k==12){
                    k=0;
                    x++
                }
            }
        }
    F()
    }
});
G.delegate("td:not(.dp_disabled, .dp_weekend_disabled, .dp_not_in_month)",{
    click:function(){
        e.val(B(new Date(x,k,m(a(this).html()))));
        e.focus();
        M.hide()
        }
    });
b.delegate("td",{
    click:function(){
        var R=a(this).attr("class").match(/dp\_month\_([0-9]+)/);
        k=m(R[1]);
        c="days";
        F()
        }
    });
l.delegate("td",{
    click:function(){
        x=m(a(this).html());
        c="months";
        F()
        }
    });
a("body").bind("mousedown",function(R){
    if(C.css("display")=="block"){
        if(a(R.target).get(0)===p.get(0)){
            return true
            }
            if(a(R.target).parents().filter(".Zebra_DatePicker").length==0){
            M.hide()
            }
        }
});
o=[];
a.each(M.settings.disabled_dates,function(){
    var V=this.split(" ");
    for(var U=0;U<4;U++){
        if(!V[U]){
            V[U]="*"
            }
            V[U]=(a.inArray(",",V[U])>-1?V[U].split(","):new Array(V[U]));
        for(var T=0;T<V[U].length;T++){
            if(a.inArray("-",V[U][T])>-1){
                var S=V[U][T].match(/^([0-9]+)\-([0-9]+)/);
                if(null!=S){
                    for(var R=m(S[1]);R<=m(S[2]);R++){
                        if(a.inArray(R,V[U])==-1){
                            V[U].push(R+"")
                            }
                        }
                    V[U].splice(T,1)
                }
            }
        }
    for(T=0;T<V[U].length;T++){
    V[U][T]=(isNaN(m(V[U][T]))?V[U][T]:m(V[U][T]))
    }
}
o.push(V)
})
};

M.hide=function(){
    E("hide");
    C.css("display","none")
    };
    
M.show=function(){
    F();
    var W=C.outerWidth(),V=C.outerHeight(),U=p.offset().left+M.settings.offset[0],T=p.offset().top-V+M.settings.offset[1],P=a(window).width(),S=a(window).height(),R=a(window).scrollTop(),Q=a(window).scrollLeft();
    if(U+W>Q+P){
        U=Q+P-W
        }
        if(U<Q){
        U=Q
        }
        if(T+V>R+S){
        T=R+S-V
        }
        if(T<R){
        T=R
        }
        C.css({
        left:U,
        top:T
    });
    C.fadeIn(a.browser.msie&&a.browser.version.match(/^[6-8]/)?0:150,"linear");
    E()
    };
    
var h=function(T){
    if(a.trim(T)!=""){
        var ac=y(M.settings.format.replace(/\s/g,"")),ab=["d","D","j","l","N","S","w","F","m","M","n","Y","y"],X=new Array,aa=new Array;
        for(var W=0;W<ab.length;W++){
            if((position=ac.indexOf(ab[W]))>-1){
                X.push({
                    character:ab[W],
                    position:position
                })
                }
            }
        X.sort(function(ae,ad){
        return ae.position-ad.position
        });
    a.each(X,function(ae,ad){
        switch(ad.character){
            case"d":
                aa.push("0[1-9]|[12][0-9]|3[01]");
                break;
            case"D":
                aa.push("[a-z]{3}");
                break;
            case"j":
                aa.push("[1-9]|[12][0-9]|3[01]");
                break;
            case"l":
                aa.push("[a-z]+");
                break;
            case"N":
                aa.push("[1-7]");
                break;
            case"S":
                aa.push("st|nd|rd|th");
                break;
            case"w":
                aa.push("[0-6]");
                break;
            case"F":
                aa.push("[a-z]+");
                break;
            case"m":
                aa.push("0[1-9]|1[012]+");
                break;
            case"M":
                aa.push("[a-z]{3}");
                break;
            case"n":
                aa.push("[1-9]|1[012]");
                break;
            case"Y":
                aa.push("[0-9]{4}");
                break;
            case"y":
                aa.push("[0-9]{2}");
                break
                }
            });
if(aa.length){
    X.reverse();
    a.each(X,function(ae,ad){
        ac=ac.replace(ad.character,"("+aa[aa.length-ae-1]+")")
        });
    aa=new RegExp("^"+ac+"$","ig");
    if((segments=aa.exec(T.replace(/\s/g,"")))){
        var R,Q,Z,Y=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],V=["January","February","March","April","May","June","July","August","September","October","November","December"],S,P=true;
        X.reverse();
        a.each(X,function(ae,ad){
            if(!P){
                return true
                }
                switch(ad.character){
                case"m":case"n":
                    Q=m(segments[ae+1]);
                    break;
                case"d":case"j":
                    R=m(segments[ae+1]);
                    break;
                case"D":case"l":case"F":case"M":
                    if(ad.character=="D"||ad.character=="l"){
                    S=M.settings.days
                    }else{
                    S=M.settings.months
                    }
                    P=false;
                a.each(S,function(af,ag){
                    if(P){
                        return true
                        }
                        if(segments[ae+1].toLowerCase()==ag.substring(0,(ad.character=="D"||ad.character=="M"?3:ag.length)).toLowerCase()){
                        switch(ad.character){
                            case"D":
                                segments[ae+1]=Y[af].substring(0,3);
                                break;
                            case"l":
                                segments[ae+1]=Y[af];
                                break;
                            case"F":
                                segments[ae+1]=V[af];
                                Q=af+1;
                                break;
                            case"M":
                                segments[ae+1]=V[af].substring(0,3);
                                Q=af+1;
                                break
                                }
                                P=true
                        }
                    });
                break;
            case"Y":
                Z=m(segments[ae+1]);
                break;
            case"y":
                Z="19"+m(segments[ae+1]);
                break
                }
            });
if(P){
    var U=new Date(Z,Q-1,R);
    if(U.getFullYear()==Z&&U.getDate()==R&&U.getMonth()==(Q-1)){
        return U
        }
    }
}
}
return false
}
};

var i=function(P){
    if(a.browser.mozilla){
        P.css("MozUserSelect","none")
        }else{
        if(a.browser.msie){
            P.bind("selectstart",function(){
                return false
                })
            }else{
            P.mousedown(function(){
                return false
                })
            }
        }
};

var y=function(P){
    return P.replace(/([-.*+?^${}()|[\]\/\\])/g,"\\$1")
    };
    
var B=function(Q){
    var Y="",T=Q.getDate(),X=Q.getDay(),R=M.settings.days[X],P=Q.getMonth()+1,V=M.settings.months[P-1],W=Q.getFullYear()+"";
    for(var U=0;U<M.settings.format.length;U++){
        var S=M.settings.format.charAt(U);
        switch(S){
            case"y":
                W=W.substr(2);
            case"Y":
                Y+=W;
                break;
            case"m":
                P=q(P,2);
            case"n":
                Y+=P;
                break;
            case"M":
                V=V.substr(0,3);
            case"F":
                Y+=V;
                break;
            case"d":
                T=q(T,2);
            case"j":
                Y+=T;
                break;
            case"D":
                R=R.substr(0,3);
            case"l":
                Y+=R;
                break;
            case"N":
                X++;
            case"w":
                Y+=X;
                break;
            case"S":
                if(T%10==1&&T!="11"){
                Y+="st"
                }else{
                if(T%10==2&&T!="12"){
                    Y+="nd"
                    }else{
                    if(T%10==3&&T!="13"){
                        Y+="rd"
                        }else{
                        Y+="th"
                        }
                    }
            }
            break;
    default:
        Y+=S
        }
    }
return Y
};

var w=function(){
    var S=new Date(x,k+1,0).getDate(),R=new Date(x,k,1).getDay(),U=new Date(x,k,0).getDate(),Q=R-M.settings.first_day_of_week;
    Q=Q<0?7+Q:Q;
    N(M.settings.months[k]+", "+x);
    var V="<tr>";
    for(var T=0;T<7;T++){
        V+="<th>"+M.settings.days[(M.settings.first_day_of_week+T)%7].substr(0,2)+"</th>"
        }
        V+="</tr><tr>";
    for(var T=0;T<42;T++){
        if(T>0&&T%7==0){
            V+="</tr><tr>"
            }
            var X=(T-Q+1);
        if(T<Q){
            V+='<td class="dp_not_in_month">'+(U-Q+T+1)+"</td>"
            }else{
            if(X>S){
                V+='<td class="dp_not_in_month">'+(X-S)+"</td>"
                }else{
                var W=(M.settings.first_day_of_week+T)%7,P=m(O(x,q(k,2),q(X,2)));
                class_name="";
                if(J(P)){
                    if(a.inArray(W,M.settings.weekend_days)>-1){
                        class_name="dp_weekend_disabled"
                        }else{
                        class_name+=" dp_disabled"
                        }
                    }else{
                if(a.inArray(W,M.settings.weekend_days)>-1){
                    class_name="dp_weekend"
                    }
                    if(k==K&&x==L&&z==X){
                    class_name+=" dp_selected"
                    }else{
                    if(k==j&&x==u&&g==X){
                        class_name+=" dp_current"
                        }
                    }
            }
        V+="<td"+(class_name!=""?' class="'+a.trim(class_name)+'"':"")+">"+q(X,2)+"</td>"
        }
    }
}
V+="</tr>";
G.html(a(V));
G.css("display","")
};

var d=function(){
    N(x);
    var R="<tr>";
    for(var Q=0;Q<12;Q++){
        if(Q>0&&Q%3==0){
            R+="</tr><tr>"
            }
            var S="dp_month_"+Q,P=m(O(x,q(Q,2)));
        if(J(P)){
            S+=" dp_disabled"
            }else{
            if(j==Q&&u==x){
                S+=" dp_current"
                }
            }
        R+='<td class="'+a.trim(S)+'">'+M.settings.months[Q].substr(0,3)+"</td>"
        }
        R+="</tr>";
b.html(a(R));
b.css("display","")
};

var v=function(){
    N(x-7+" - "+(x+4));
    var R="<tr>";
    for(var Q=0;Q<12;Q++){
        if(Q>0&&Q%3==0){
            R+="</tr><tr>"
            }
            var S="",P=m(x-7+Q);
        if(J(P)){
            S+=" dp_disabled"
            }else{
            if(u==(x-7+Q)){
                S+=" dp_current"
                }
            }
        R+="<td"+(a.trim(S)!=""?' class="'+a.trim(S)+'"':"")+">"+(x-7+Q)+"</td>"
        }
        R+="</tr>";
l.html(a(R));
l.css("display","")
};

var E=function(P){
    if(a.browser.msie&&a.browser.version.match(/^6/)){
        if(!H){
            var R=m(C.css("zIndex"))-1;
            H=jQuery("<iframe>",{
                src:'javascript:document.write("")',
                scrolling:"no",
                frameborder:0,
                allowtransparency:"true",
                css:{
                    zIndex:R,
                    position:"absolute",
                    top:-1000,
                    left:-1000,
                    width:C.outerWidth(),
                    height:C.outerHeight(),
                    filter:"progid:DXImageTransform.Microsoft.Alpha(opacity=0)",
                    display:"none"
                }
            });
        a("body").append(H)
        }
        switch(P){
        case"hide":
            H.css("display","none");
            break;
        default:
            var Q=C.offset();
            H.css({
            top:Q.top,
            left:Q.left,
            display:"block"
        })
        }
        }
};

var J=function(R){
    if(n!==0){
        var P=(R+"").length;
        if(P==8&&((n&&R<O(t,q(s,2),q(I,2)))||(!n&&R>O(t,q(s,2),q(I,2))))){
            return true
            }else{
            if(P==6&&((n&&R<O(t,q(s,2)))||(!n&&R>O(t,q(s,2))))){
                return true
                }else{
                if(P==4&&((n&&R<t)||(!n&&R>t))){
                    return true
                    }
                }
        }
}
if(o){
    R=R+"";
    var T=m(R.substr(0,4)),U=m(R.substr(4,2))+1,Q=m(R.substr(6,2)),S=false;
    a.each(o,function(){
        if(S){
            return
        }
        var W=this;
        if(a.inArray(T,W[2])>-1||a.inArray("*",W[2])>-1){
            if((undefined!=U&&a.inArray(U,W[1])>-1)||a.inArray("*",W[1])>-1){
                if((undefined!=Q&&a.inArray(Q,W[0])>-1)||a.inArray("*",W[0])>-1){
                    if(W[3]=="*"){
                        return(S=true)
                        }
                        var V=new Date(T,U-1,Q).getDay();
                    if(a.inArray(V,W[3])>-1){
                        return(S=true)
                        }
                    }
            }
    }
});
if(S){
    return true
    }
}
return false
};

var N=function(P){
    f.find(".dp_caption").html(P);
    if(n!==0){
        var Q=x,S=k,R;
        if(c=="days"){
            if(n&&--S<0){
                S=11;
                Q--
            }else{
                if(!n&&++S>11){
                    S=0;
                    Q++
                }
            }
            R=O(Q,q(S,2))
        }else{
        if(c=="months"){
            if(n){
                Q--
            }else{
                Q++
            }
            R=Q
            }else{
            if(c=="years"){
                if(n){
                    Q-=7
                    }else{
                    Q+=7
                    }
                    R=Q
                }
            }
    }
if(J(R)){
    f.find(n?".dp_previous":".dp_next").addClass("dp_blocked");
    f.find(n?".dp_previous":".dp_next").removeClass("dp_hover")
    }else{
    f.find(n?".dp_previous":".dp_next").removeClass("dp_blocked")
    }
}
};

var F=function(){
    if(G.text()==""||c=="days"){
        if(G.text()==""){
            C.css({
                left:-1000,
                display:"block"
            });
            w();
            var Q=G.outerWidth(),P=G.outerHeight();
            f.css("width",Q);
            b.css({
                width:Q,
                height:P
            });
            l.css({
                width:Q,
                height:P
            });
            C.css({
                display:"none"
            })
            }else{
            w()
            }
            b.css("display","none");
        l.css("display","none")
        }else{
        if(c=="months"){
            d();
            G.css("display","none");
            l.css("display","none")
            }else{
            if(c=="years"){
                v();
                G.css("display","none");
                b.css("display","none")
                }
            }
    }
};

var q=function(Q,P){
    Q+="";
    while(Q.length<P){
        Q="0"+Q
        }
        return Q
    };
    
var O=function(){
    var Q="";
    for(var P=0;P<arguments.length;P++){
        Q+=(arguments[P]+"")
        }
        return Q
    };
    
var m=function(P){
    return parseInt((P===true||P===false?0:P),10)
    };
    
M.init()
};

a.fn.Zebra_DatePicker=function(b){
    return this.each(function(){
        if(undefined==a(this).data("Zebra_DatePicker")){
            var c=new a.Zebra_DatePicker(this,b);
            a(this).data("Zebra_DatePicker",c)
            }
        })
}
})(jQuery);