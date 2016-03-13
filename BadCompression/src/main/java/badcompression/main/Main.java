/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompression.main;

import badcompression.compression.CompressionResults;
import badcompression.compression.Compressor;
import badcompression.huffman.HuffmanCompressionByte;
import badcompression.huffman.HuffmanCompressionUTF8;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antti
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //For testing:
        //args = new String[]{"uncomp", "/home/antti/comp/test.txt.utf8.badComp"};
        if (args.length < 2) {
            System.out.println("Not enough arguments given.");
            printHelp();
            return;
        } else if (args.length > 3) {
            System.out.println("Too many arguments given.");
            printHelp();
            return;
        } else if (args[0].equals("-help") || args[0].equals("--help")) {
            printHelp();
            return;
        } else if (!args[0].equals("comp") && !args[0].equals("uncomp")) {
            System.out.println("Command should be comp or uncomp.");
            printHelp();
            return;
        } else if ((args[0].equals("uncomp") && args.length > 2)) {
            System.out.println("Too many argumens for uncompression.");
            printHelp();
            return;
        } else if ((args[0].equals("comp") && args.length == 3) && !args[1].equals("utf8")) {
            System.out.println("Option can be utf8.");
            printHelp();
            return;
        }      

        String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

        String command = args[0];
        String sourceFilePath;
        String destFilePath = null;
        boolean utf8 = false;
        if (args.length == 3) {
            sourceFilePath = args[2];
            destFilePath = sourceFilePath + ".utf8.badComp";
            utf8 = true;
            if (!args[1].equals("utf8")) {
                System.out.println("Argument error: 2nd argument should be UTF8 or empty.");
            }
        } else {
            sourceFilePath = args[1];
            destFilePath = sourceFilePath + ".badComp";
        }

        if (command.equals("comp")) {
            //UTF8
            if (utf8) {
                System.out.println(args[2]);
                compress(new HuffmanCompressionUTF8(), sourceFilePath, destFilePath);
            } else {
                compress(new HuffmanCompressionByte(), sourceFilePath, destFilePath);
            }
        } else {
            String[] splitted = args[1].split("\\.");
            if (splitted.length == 1 || !splitted[splitted.length - 1].equals("badComp")) {
                //TODO: check first.
                System.out.println("File not compressed with BadCompression.");
            } else if (splitted[splitted.length - 2].equals("utf8")) {
                String target = splitted[0];
                for (int i = 1; i < splitted.length - 2; i++) {
                    target += "." + splitted[i];
                }
                uncompress(new HuffmanCompressionUTF8(), args[1], target);
            } else {
                String target = splitted[0];
                for (int i = 1; i < splitted.length - 1; i++) {
                    target += "." + splitted[i];
                }
                uncompress(new HuffmanCompressionByte(), args[1], target);
            }
        }
    }

    private static void compress(Compressor comp, String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        checkFiles(targetFile, sourceFile);
        CompressionResults results = null;
        try {
            results = comp.compress(sourceFile, targetFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.isFailure()) {
            System.out.println("FAILURE:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("File compressed to: " + target);
        }
    }

    private static void uncompress(Compressor comp, String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        checkFiles(targetFile, sourceFile);
        CompressionResults results = null;
        try {
            results = comp.uncompress(sourceFile, targetFile);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (results.isFailure()) {
            System.out.println("Failure:");
            System.out.println(results.getFailureReason());
        } else {
            System.out.println("File uncompressed to: " + target);

        }
    }

    private static void checkFiles(File target, File source) {
        try {
            if (!target.createNewFile()) {
                System.out.println("FAILURE:");
                System.out.println("Target file " + target + " already excists.");
            } else if (!target.canWrite()) {
                System.out.println("FILURE:");
                System.out.println("Cant write to target file: " + target + ".");
            } else if (!source.getAbsoluteFile().exists()) {
                System.out.println("FAILURE:");
                System.out.println("Source file: " + source + " doesn't excist.");
            } else if (!source.canRead()) {
                System.out.println("FAILURE: ");
                System.out.println("Cant read source file: " + source + ".");
            } else {
                //No failures.
                return;
            }
        } catch (IOException ex) {
            System.out.println("FILURE: cant create file: " + target + ".");
        }
        //Exit program
        System.exit(0);
    }

    private static void printHelp() {
        System.out.println("");
        System.out.println("USAGE:");
        System.out.println("java -jar BadCompression.jar <command> <optional option> <file");
        System.out.println("<command>: comp (compress) or uncomp (uncompress)");
        System.out.println("<optional option>: utf8 for utf8 file compression.");
        System.out.println("<absolute file path> (example: /home/user/file.txt)");
        System.out.println("");
        System.out.println("EXAMPLES:");
        System.out.println("Compress utf-8 file:");
        System.out.println("java -jar BadCompression.jar comp utf8 /home/user/utf8file.txt");
        System.out.println("Compress any file:");
        System.out.println("java -jar BadComp.jar comp /home/user/file.txt");
        System.out.println("Uncompress file:");
        System.out.println("java -jar BadComp.jar uncomp /home/user/file.txt.utf8.badComp");
        System.out.println("java -jar BadComp.jar uncomp /home/user/file.txt.badComp");
    }

}
