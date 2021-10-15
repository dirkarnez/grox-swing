import com.groupon.grox.Store;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class State {
    public static final String defaultButtonText = "Click here";

    private final String buttonText;

    public State(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return this.buttonText;
    }
}

public class Main {
    public static void main(String[] args) {
        Store<State> store = new Store<>(new State(State.defaultButtonText));

        JFrame f = new JFrame();
        JButton b = new JButton();

        b.addActionListener(actionEvent -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
            store.dispatch(oldState -> new State(String.format("Clicked at %s", sdf.format(new Date(System.currentTimeMillis())))));
        });

        store.subscribe(newState -> {
            b.setText(newState.getButtonText());
        });

        b.setBounds(50, 100, 300, 30);
        f.add(b);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}