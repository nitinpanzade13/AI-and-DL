// ForwardChaining.java
import java.util.*;

public class ForwardChaining {
    static class Rule { List<String> ifs; String then;
        Rule(List<String> a,String c){ifs=a; then=c;}
    }
    Set<String> facts=new HashSet<>();
    List<Rule> rules=new ArrayList<>();

    void addFact(String f){ facts.add(f); }
    void addRule(List<String>a,String c){ rules.add(new Rule(a,c)); }

    void run(){
        boolean changed;
        do{
            changed=false;
            for(Rule r:rules){
                if(facts.contains(r.then)) continue;
                if(facts.containsAll(r.ifs)){
                    facts.add(r.then); changed=true;
                    System.out.println("Derived: "+r.then);
                }
            }
        }while(changed);
    }

    public static void main(String[] args){
        ForwardChaining fc=new ForwardChaining();
        fc.addFact("fever"); fc.addFact("cough");
        fc.addRule(Arrays.asList("fever","cough"),"flu");
        fc.addRule(Arrays.asList("flu"),"rest");
        fc.run();
        System.out.println("Facts: "+fc.facts);
    }
}
