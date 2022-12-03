import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPBike extends CSP {

    static Set<Object> varBikeCol =  new HashSet<Object>(Arrays.asList(new String[] {"black", "blue", "green", "red", "white"}));
    static Set<Object> varNames =  new HashSet<Object>(Arrays.asList(new String[] {"adrian", "charles", "henry", "joel", "richard"}));
    static Set<Object> varSandwich =  new HashSet<Object>(Arrays.asList(new String[] {"bacon", "chicken", "cheese", "pepperoni", "tuna"}));
    static Set<Object> varJuice =  new HashSet<Object>(Arrays.asList(new String[] {"apple", "cranberry", "grapefruit", "orange", "pineapple"}));
    static Set<Object> varAge =  new HashSet<Object>(Arrays.asList(new String[] {"12yrs", "13yrs", "14yrs", "15yrs", "16yrs"}));
    static Set<Object> varSport =  new HashSet<Object>(Arrays.asList(new String[] {"basketball", "baseball", "hockey", "soccer", "swimming"}));

    public boolean isGood(Object X, Object Y, Object x, Object y) {

        if(!C.containsKey(X))
            return true;

        if(!C.get(X).contains(Y))
            return true;

        //The owner of the White bike is somewhere between the 15-year-old boy and the youngest boy, in that order.
        if(X.equals("white") && Y.equals("15yrs") && (Integer)x<(Integer)y)
            return false;
        if(X.equals("white") && Y.equals("12yrs") && (Integer)x>(Integer)y)
            return false;

        //Henry is exactly to the left of the Soccer fan.
        if(X.equals("henry") && Y.equals("soccer") && (Integer)y-(Integer)x!=1)
            return false;

        //The boy who is going to drink Grapefruit juice is somewhere between who brought Tuna sandwich and who brought Pineapple juice, in that order.
        if(X.equals("grapefruit") && Y.equals("tuna") && (Integer)x<(Integer)y)
            return false;
        if(X.equals("grapefruit") && Y.equals("pineapple") && (Integer)x>(Integer)y)
            return false;

        //The one who likes Swimming is next to the friend who likes Baseball.
        if(X.equals("swimming") && Y.equals("baseball") && Math.abs((Integer)x-(Integer)y)!=1)
            return false;

        //The cyclist that brought Pineapple juice is somewhere between the 14-year-old and the boy that brought Orange juice, in that order.
        if(X.equals("pineapple") && Y.equals("14yrs") && (Integer)x<(Integer)y)
            return false;
        if(X.equals("pineapple") && Y.equals("orange") && (Integer)x>(Integer)y)
            return false;

        //The boy who likes the sport played on ice is going to eat Pepperoni sandwich.
        if(X.equals("hockey") && Y.equals("pepperoni") && !x.equals(y))
            return false;

        //The boy riding the White bike is somewhere between the boys riding the blue and the black bicycles, in that order.
        if(X.equals("white") && Y.equals("blue") && (Integer)x<=(Integer)y)
            return false;
        if(X.equals("white") && Y.equals("black") && (Integer)x>=(Integer)y)
            return false;

        //Joel is next to the 16-year-old cyclist.
        if(X.equals("joel") && Y.equals("16yrs") && Math.abs((Integer)x-(Integer)y)!=1)
            return false;

        //Adrian is exactly to the left of the boy who is going to eat Pepperoni sandwich.
        if(X.equals("adrian") && Y.equals("pepperoni") && (Integer)y-(Integer)x!=1)
            return false;

        //The 12-year-old is somewhere between the 14-year-old and the oldest boy, in that order.
        if(X.equals("12yrs") && Y.equals("14yrs") && (Integer)x<=(Integer)y)
            return false;
        if(X.equals("12yrs") && Y.equals("16yrs") && (Integer)x>=(Integer)y)
            return false;

        //The boy who is going to eat Bacon sandwich is somewhere to the right of the owner of the White bicycle.
        if(X.equals("bacon") && Y.equals("white") && (Integer)x<=(Integer)y)
            return false;

        //The 16-year-old brought Cheese sandwich.
        if(X.equals("16yrs") && Y.equals("cheese") && !x.equals(y))
            return false;

        //The cyclist riding the White bike is somewhere between Richard and the boy riding the Red bike, in that order.
        if(X.equals("white") && Y.equals("richard") && (Integer)x<=(Integer)y)
            return false;
        if(X.equals("white") && Y.equals("red") && (Integer)x>=(Integer)y)
            return false;

        //The Baseball fan is next to the boy who is going to drink Apple juice.
        if(X.equals("baseball") && Y.equals("apple") && Math.abs((Integer)x-(Integer)y)!=1)
            return false;

        //Charles is somewhere between Richard and Adrian, in that order.
        if(X.equals("charles") && Y.equals("richard") && (Integer)x<=(Integer)y)
            return false;
        if(X.equals("charles") && Y.equals("adrian") && (Integer)x>=(Integer)y)
            return false;

        //Uniqueness constraints

        if(varBikeCol.contains(X) && varBikeCol.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        if(varNames.contains(X) && varNames.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        if(varSandwich.contains(X) && varSandwich.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        if(varJuice.contains(X) && varJuice.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        if(varAge.contains(X) && varAge.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        if(varSport.contains(X) && varSport.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        return true;

    }

    public static void main (String[] args) throws Exception{

        CSPBike csp = new CSPBike();
        Integer[] dom = {1,2,3,4,5};

        for(Object X : varBikeCol)
            csp.addDomain(X, dom);

        for(Object X : varNames)
            csp.addDomain(X, dom);

        for(Object X : varSandwich)
            csp.addDomain(X, dom);

        for(Object X : varJuice)
            csp.addDomain(X, dom);

        for(Object X : varAge)
            csp.addDomain(X, dom);

        for(Object X : varSport)
            csp.addDomain(X, dom);

        // Unary constraints

        //In the middle is the boy that likes Baseball.
        for(int i=1;i<=5;i++){
            if(i!=3){
                csp.D.get("baseball").remove(i);
            }
        }

        //The cyclist who is going to eat Tuna sandwich is at one of the ends.
        for(int i=1;i<=5;i++){
            if(i!=1 && i!=5){
                csp.D.get("tuna").remove(i);
            }
        }

        //The boy that is going to drink Pineapple juice is at the fourth position.
        for(int i=1;i<=5;i++){
            if(i!=4){
                csp.D.get("pineapple").remove(i);
            }
        }

        //The boy riding the Black bike is at the third position.
        for(int i=1;i<=5;i++){
            if(i!=3){
                csp.D.get("black").remove(i);
            }
        }

        //At one of the ends is the boy riding the Green bicycle.
        for(int i=1;i<=5;i++){
            if(i!=1 && i!=5){
                csp.D.get("green").remove(i);
            }
        }

        //In the fifth position is the 13-year-old boy
        for(int i=1;i<=5;i++){
            if(i!=5){
                csp.D.get("13yrs").remove(i);
            }
        }

        //The boy who likes Hockey is at the fifth position.
        for(int i=1;i<=5;i++){
            if(i!=5){
                csp.D.get("hockey").remove(i);
            }
        }

        // Binary constraints

        //The owner of the White bike is somewhere between the 15-year-old boy and the youngest boy, in that order.
        csp.addBidirectionalArc("white", "15yrs");
        csp.addBidirectionalArc("white", "12yrs");

        //Henry is exactly to the left of the Soccer fan.
        csp.addBidirectionalArc("henry", "soccer");

        //The boy who is going to drink Grapefruit juice is somewhere between who brought Tuna sandwich and who brought Pineapple juice, in that order.
        csp.addBidirectionalArc("grapefruit", "tuna");
        csp.addBidirectionalArc("grapefruit", "pineapple");

        //The one who likes Swimming is next to the friend who likes Baseball.
        csp.addBidirectionalArc("swimming", "baseball");

        //The cyclist that brought Pineapple juice is somewhere between the 14-year-old and the boy that brought Orange juice, in that order.
        csp.addBidirectionalArc("pineapple", "14yrs");
        csp.addBidirectionalArc("pineapple", "orange");

        //The boy who likes the sport played on ice is going to eat Pepperoni sandwich.
        csp.addBidirectionalArc("hockey", "pepperoni");

        //The boy riding the White bike is somewhere between the boys riding the blue and the black bicycles, in that order.
        csp.addBidirectionalArc("white", "blue");
        csp.addBidirectionalArc("white", "black");

        //Joel is next to the 16-year-old cyclist.
        csp.addBidirectionalArc("joel", "16yrs");

        //Adrian is exactly to the left of the boy who is going to eat Pepperoni sandwich.
        csp.addBidirectionalArc("adrian", "pepperoni");

        //The 12-year-old is somewhere between the 14-year-old and the oldest boy, in that order.
        csp.addBidirectionalArc("12yrs", "14yrs");
        csp.addBidirectionalArc("12yrs", "16yrs");

        //The boy who is going to eat Bacon sandwich is somewhere to the right of the owner of the White bicycle.
        csp.addBidirectionalArc("bacon", "white");

        //The 16-year-old brought Cheese sandwich.
        csp.addBidirectionalArc("16yrs", "cheese");

        //The cyclist riding the White bike is somewhere between Richard and the boy riding the Red bike, in that order.
        csp.addBidirectionalArc("white", "richard");
        csp.addBidirectionalArc("white", "red");

        //The Baseball fan is next to the boy who is going to drink Apple juice.
        csp.addBidirectionalArc("baseball", "apple");

        //Charles is somewhere between Richard and Adrian, in that order.
        csp.addBidirectionalArc("charles", "richard");
        csp.addBidirectionalArc("charles", "adrian");

        //Uniqueness constraints

        for(Object X : varBikeCol)
            for(Object Y : varBikeCol)
                csp.addBidirectionalArc(X,Y);

        for(Object X : varNames)
            for(Object Y : varNames)
                csp.addBidirectionalArc(X,Y);

        for(Object X : varSandwich)
            for(Object Y : varSandwich)
                csp.addBidirectionalArc(X,Y);

        for(Object X : varJuice)
            for(Object Y : varJuice)
                csp.addBidirectionalArc(X,Y);

        for(Object X : varAge)
            for(Object Y : varAge)
                csp.addBidirectionalArc(X,Y);

        for(Object X : varSport)
            for(Object Y : varSport)
                csp.addBidirectionalArc(X,Y);

        // Search
        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());

    }

}
