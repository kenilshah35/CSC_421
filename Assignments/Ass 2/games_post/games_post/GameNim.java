import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game{

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;

    public GameNim(){
        currentState = new StateNim();
    }

    public boolean isWinState(State state){

        StateNim nimState = (StateNim) state;
        return nimState.coins == 1;
    }

    /*
       No stuck state for this example
     */
    public boolean isStuckState(State state) {
        return false;
    }

    public Set<State> getSuccessors(State state){

        if(isWinState(state) || isStuckState(state))
            return null;

        Set<State> successors = new HashSet<State>();
        StateNim nState = (StateNim) state;

        StateNim successorState;

        for(int i=1;i<=3;i++){
            successorState = new StateNim(nState);
            successorState.coins -= i;
            successorState.player = (state.player==0 ? 1 : 0);
            successors.add(successorState);
        }
        return successors;
    }

    public double eval(State state){

        if(isWinState(state)){

            int previous_player = (state.player==0 ? 1 : 0);

            if (previous_player==0)
                return WinningScore;
            else
                return LosingScore;
        }
        return NeutralScore;
    }

    public static void main(String[] args) throws Exception{

        Game game = new GameNim();
        Search search = new Search(game);
        int depth = 13;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StateNim nextState = null;

            switch (game.currentState.player) {
                case 1 -> { //Human
                    System.out.print("Enter stones to take between 1 to 3> ");
                    int coinsTaken = Integer.parseInt(in.readLine());
                    nextState = new StateNim((StateNim) game.currentState);
                    nextState.player = 1;
                    nextState.coins -= coinsTaken;
                    System.out.println("Human: \n" + nextState);
                }
                case 0 -> { //Computer
                    nextState = (StateNim) search.bestSuccessorState(depth);
                    nextState.player = 0;
                    System.out.println("Computer: \n" + nextState);
                }
            }

            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);

            if (game.isWinState(game.currentState)){
                if (game.currentState.player == 1) //i.e. last move was by the computer
                    System.out.println("Computer wins!");
                else
                    System.out.println("You win!");

                break;
            }

        }
    }

}
