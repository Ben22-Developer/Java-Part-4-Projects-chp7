import java.util.ArrayList;

public class Instructions {

    private int word_read, word_write, word_to_load, word_to_store, word_to_add, word_to_subtract, word_to_divide, word_to_multiply, branch, branch_neg, branch_zero, halt;

    private int[] memory;

    private int accumulator, curr_memory_index, jump_to, total_instructions, instruction_counter, instruction_register;

    private boolean jump_to_updated;
    public void setup () {

        int capacity = 100;

        if (memory != null) {
            memory = null;
        }

        memory = new int[capacity];
        accumulator = 0;
        total_instructions = 0;
        instruction_counter = 0;
        instruction_register = 0;
    }

    public void takeUserInput () {

        String before_index_0, prompt_msg, program_begin_msg;
        int user_input;
        boolean valid_input;

        before_index_0 = "0";
        prompt_msg = "";

        user_input = 0;

        while (true) {

            valid_input = false;

            prompt_msg = (instruction_register < 10) ? before_index_0 + instruction_register + "  ?  " : instruction_register + " ? ";

            user_input = UI.showInputIntegerDialog(prompt_msg);

            if (user_input == -99999) {
                break;
            }

            if (user_input > -9999 && user_input < 9999) {
                memory[instruction_register] = user_input;
                valid_input = true;
            }
            else {
                UI.showMessageDialog("The number in SML should be in range -9999 <-> +9999");
            }

            if (valid_input) {
                total_instructions ++;
                instruction_register ++;
            }
        }

        program_begin_msg = "*** Program loading completed ***\n*** Program execution begins";

        UI.showMessageDialog(program_begin_msg);
    }


    public void executeUserCommands () {

        int operation, index_to_access, curr_instruction;
        boolean continue_loop;

        operation = 0;
        index_to_access = 0;
        continue_loop = true;

        while (instruction_counter < total_instructions && continue_loop) {

            curr_instruction = memory[instruction_counter];

            operation = curr_instruction/100;
            index_to_access = curr_instruction % 100;

            continue_loop = commandExecution_Controller (operation, index_to_access);

            if ((operation == 40 || operation == 41 || operation == 42) && jump_to_updated) {
                instruction_counter = jump_to;
                jump_to_updated = false;
            }
            else {
                instruction_counter ++;
            }
        }

        computer_dump (operation, index_to_access);
        UI.showMessageDialog("Program terminates.");
    }

    private boolean commandExecution_Controller (int to_do_operation, int index_to_access) {

        boolean operation_successful = true;

        switch (to_do_operation) {

            case 10:
                int read_data = CommandsOperations.toRead();
                storeData_InMemory (read_data,index_to_access);
            return operation_successful;

            case 11:
                CommandsOperations.toWrite(memory, index_to_access);
            return operation_successful;

            case 20:
                accumulator = CommandsOperations.toLoad(memory, index_to_access);
            return operation_successful;

            case 21:
                storeData_InMemory(accumulator,index_to_access);
            return operation_successful;

            case 30:
                accumulator = CommandsOperations.toAdd(memory, index_to_access, accumulator);
            return operation_successful;

            case 31:
                accumulator = CommandsOperations.toSubtract(memory, index_to_access, accumulator);
            return operation_successful;

            case 32:
                accumulator = CommandsOperations.toDivide(memory, index_to_access, accumulator);
            return operation_successful;

            case 33:
                accumulator = CommandsOperations.toMultiply(memory, index_to_access, accumulator);
            return operation_successful;

            case 34:
                accumulator = CommandsOperations.getRemainder(memory, index_to_access, accumulator);
            return operation_successful;

            case 35:
                accumulator = CommandsOperations.getExponential(memory, index_to_access, accumulator);
            return operation_successful;

            case 40:
                conditionController_JustBranch(index_to_access);
            return operation_successful;

            case 41:
                conditionController_BranchNeg(index_to_access);
            return operation_successful;

            case 42:
                conditionController_BranchZero(index_to_access);
            return operation_successful;
            case 43:
            return false;
        }

//      it will be out of switch if the command given doesn't exist!

        UI.showMessageDialog("Invalid command!\nCommand: "+to_do_operation);
        operation_successful = false;
        return operation_successful;
    }

    private void conditionController_JustBranch (int index_to_access) {
        jump_to = index_to_access;
        jump_to_updated = true;
    }

    private void conditionController_BranchNeg (int index_to_access) {
        boolean accumulator_is_negative = CommandsOperations.toCheckBranchNeg (accumulator);
        if (accumulator_is_negative) {
            jump_to = index_to_access;
            jump_to_updated = true;
        }
    }

    private void conditionController_BranchZero (int index_to_access) {

        boolean accumulator_is_zero = CommandsOperations.toCheckBranchZero (accumulator);
        if (accumulator_is_zero) {
            jump_to = index_to_access;
            jump_to_updated = true;
        }
    }

    private void storeData_InMemory (int data, int index_to_access) {
        memory[index_to_access] = data;
    }

    private void computer_dump (int last_operation, int last_accessed_index) {

        String display, title, in_memory;
        boolean new_row = false;
        int row_head = 0;

        title = "REGISTERS\nAccumulator: "+accumulator+"\nInstruction Counter: "+instruction_counter+"\nInstruction register: "+instruction_register+"\nLast operation: "+last_operation+"\nLast accessed index: "+last_accessed_index+"\n\n";

        in_memory = "MEMORY:\n";

        in_memory += "       0       1       2       3       4       5       6       7       8       9";

        for (int i = 0; i < memory.length; i++) {

            if (i % 10 == 0) {
                new_row = true;
            }

            if (new_row) {
                in_memory += "\n"+row_head+"  ";
                row_head += 10;
                new_row = false;
            }

            in_memory += (memory[i] == 0) ? "0000   " : memory[i]+"   ";
        }

        display = title + in_memory;
        UI.showMessageDialog(display);
    }
}
