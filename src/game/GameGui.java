package game;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.function.Consumer;

public class GameGui extends JFrame {
	
	public void setCommandHandler(Consumer<String> handler) {
	    this.commandHandler = handler;
	}
	private Consumer<String> commandHandler;
    private JTextArea storyArea;
    private JTextField commandField;
    private JButton submitButton;

    private final Color PINK = new Color(255, 192, 220);
    private final Color LIGHT_PINK = new Color(255, 235, 245);
    private final Color PURPLE = new Color(155, 100, 200);
    private final Color LAVENDER = new Color(235, 215, 255);
    private final Color DARK_PURPLE = new Color(95, 45, 125);

    public GameGui() {
        setTitle("My Magical Adventure");
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Pink and purple gradient background
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gradient = new GradientPaint(
                    0, 0, PINK,
                    getWidth(), getHeight(), LAVENDER
                );

                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Glitter and sparkles
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Serif", Font.BOLD, 20));

                g2.drawString("✦", 25, 40);
                g2.drawString("✧", 90, 100);
                g2.drawString("✦", 740, 50);
                g2.drawString("✧", 700, 150);
                g2.drawString("✦", 45, 300);
                g2.drawString("✧", 750, 350);
                g2.drawString("✦", 100, 500);
                g2.drawString("✧", 650, 500);
                g2.drawString("♡", 25, 160);
                g2.drawString("♡", 755, 240);
            }
        };

        mainPanel.setBorder(
            new EmptyBorder(15, 25, 20, 25)
        );
        mainPanel.setOpaque(false);

        // Title
        JLabel titleLabel = new JLabel(
            "♡ My Magical Adventure ♡",
            SwingConstants.CENTER
        );
        titleLabel.setFont(
            new Font("Serif", Font.BOLD, 30)
        );
        titleLabel.setForeground(DARK_PURPLE);

        JLabel subtitleLabel = new JLabel(
            "✧ Your story, your magic, your adventure ✧",
            SwingConstants.CENTER
        );
        subtitleLabel.setFont(
            new Font("SansSerif", Font.ITALIC, 14)
        );
        subtitleLabel.setForeground(PURPLE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        // Story area
        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(
            new Font("Serif", Font.PLAIN, 17)
        );
        storyArea.setForeground(DARK_PURPLE);
        storyArea.setBackground(LIGHT_PINK);
        storyArea.setMargin(new Insets(15, 15, 15, 15));

        storyArea.setText(
            "Welcome, magical adventurer! ♡\n\n" +
            "✨ Your story is waiting to begin... ✨\n\n" +
            "Explore mysterious places, discover hidden secrets, " +
            "and find your way through a magical world!\n\n" +
            "Type 'help' to see available commands, or type " +
            "'look' to explore your surroundings.\n\n" +
            "♡ Let the adventure begin! ♡\n\n"
        );

        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setBorder(
            new LineBorder(PURPLE, 3, true)
        );
        scrollPane.getViewport().setBackground(LIGHT_PINK);

        // Command input box
        commandField = new JTextField();
        commandField.setFont(
            new Font("SansSerif", Font.PLAIN, 16)
        );
        commandField.setForeground(DARK_PURPLE);
        commandField.setBackground(Color.WHITE);
        commandField.setCaretColor(PURPLE);
        commandField.setBorder(
            new CompoundBorder(
                new LineBorder(PINK, 2, true),
                new EmptyBorder(8, 10, 8, 10)
            )
        );

        // Pink submit button
        submitButton = new JButton("♡ Submit ♡");
        submitButton.setFont(
            new Font("SansSerif", Font.BOLD, 15)
        );
        submitButton.setForeground(DARK_PURPLE);
        submitButton.setBackground(PINK);
        submitButton.setFocusPainted(false);
        submitButton.setOpaque(true);
        submitButton.setBorder(
            new CompoundBorder(
                new LineBorder(PURPLE, 2, true),
                new EmptyBorder(8, 12, 8, 12)
            )
        );
        submitButton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        JPanel inputPanel = new JPanel(new BorderLayout(10, 5));
        inputPanel.setOpaque(false);
        inputPanel.add(commandField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        JLabel hintLabel = new JLabel(
            "Enter a command to continue your journey! 💗",
            SwingConstants.CENTER
        );
        hintLabel.setForeground(DARK_PURPLE);
        hintLabel.setFont(
            new Font("SansSerif", Font.ITALIC, 12)
        );

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 8));
        bottomPanel.setOpaque(false);
        bottomPanel.add(hintLabel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Submit using the button or Enter
        submitButton.addActionListener(e -> submitCommand());
        commandField.addActionListener(e -> submitCommand());

        setVisible(true);
    }

    // Display messages from your game
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            storyArea.append(message + "\n\n");
            storyArea.setCaretPosition(
                storyArea.getDocument().getLength()
            );
        });
    }

    // Get the player's typed command
    public String getCommand() {
        return commandField.getText().trim();
    }

    // Clear the command input
    public void clearCommand() {
        commandField.setText("");
    }

    // Handle submitted commands

private void submitCommand() {
    String command = getCommand();

    if (command.isEmpty()) {
        return;
    }

    storyArea.append("♡ You: " + command + "\n\n");
    commandField.setText("");

    if (commandHandler != null) {
        commandHandler.accept(command);
    } else {
        displayMessage("♡ Start your magical adventure!");
    }

    commandField.requestFocusInWindow();
}

    // Run this class by itself to preview the GUI
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new GameGui());
}
}
