import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SimulationEinsTest {

    @Test
    public void testSimulationRunsForGivenTime() {
        // Set System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Run the simulation for 5 seconds
        new Thread(() -> SimulationEins.main(new String[]{})).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reset System.out to its original state
        System.setOut(originalOut);

        // Ensure that some output was produced during the simulation run
        String output = outContent.toString();
        assertFalse(output.isEmpty());
    }


}