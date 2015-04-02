package NURE_BPID_VIGENERE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Alexander on 02.04.2015.
 */
public class MainWindow extends JFrame {
    private JButton buttonEncrypt;
    private JPanel mainContentPanel;
    private JTextArea textAreaOriginalText;
    private JTextArea textAreaEncryptedText;
    private JButton buttonDecrypt;
    private JTextArea textAreaDecryptedText;
    private JTextField textFieldPassword;
    private JLabel labelOriginText;
    private JLabel labelPassword;
    private JLabel labelCipher;
    private JLabel labelDecryption;

    public MainWindow() {
        super();
        setVisible(true);
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainContentPanel);
        initActions();
    }

    private void initActions() {
        buttonEncrypt.setEnabled(false);
        buttonDecrypt.setEnabled(false);
        textAreaEncryptedText.setEditable(false);
        textAreaDecryptedText.setEditable(false);
        textAreaOriginalText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkAbilityToEncrypDecryptActions();
            }
        });
        textFieldPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkAbilityToEncrypDecryptActions();
            }
        });
        buttonEncrypt.addActionListener((e) -> encryptAction());
        buttonDecrypt.addActionListener((e) -> decryptAction());
    }

    private void encryptAction() {
        String text = textAreaOriginalText.getText();
        String pass = textFieldPassword.getText();
        String res = Vigenere.encrypt(text, pass);
        textAreaEncryptedText.setText(res);
        checkAbilityToEncrypDecryptActions();
    }

    private void decryptAction() {
        String text = textAreaEncryptedText.getText();
        String pass = textFieldPassword.getText();
        String res = Vigenere.decrypt(text, pass);
        textAreaDecryptedText.setText(res);
    }

    private void checkAbilityToEncrypDecryptActions() {
        if (textFieldPassword.getText().length() > 0 && textAreaOriginalText.getText().length() > 0)
            buttonEncrypt.setEnabled(true);
        else buttonEncrypt.setEnabled(false);

        if (textAreaEncryptedText.getText().length() > 0)
            buttonDecrypt.setEnabled(true);
        else buttonDecrypt.setEnabled(false);
    }

    public static void main(String argc[]) {
        new MainWindow().setTitle("Vigenere");
    }
}
