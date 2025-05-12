import java.io.*;
import java.util.*;

public class TagChecker {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the HTML file.");
            return;
        }

        String filePath = args[0];
        StringStackImpl<String> tagStack = new StringStackImpl<>();

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                List<String> foundTags = extractTags(currentLine);

                for (String tag : foundTags) {
                    if (isSelfClosing(tag)) {
                        continue;
                    } else if (isOpeningTag(tag)) {
                        tagStack.push(tag);
                    } else if (isClosingTag(tag)) {
                        if (tagStack.isEmpty()) {
                            System.out.println("Closing tag without matching opening tag: " + tag);
                            return;
                        }

                        String lastOpenTag = tagStack.pop();
                        if (!tagsMatch(lastOpenTag, tag)) {
                            System.out.println("Tag don't match: " + lastOpenTag + " and " + tag);
                            return;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        if (!tagStack.isEmpty()) {
            System.out.println("Some opening tags do not have closing tags.");
        } else {
            System.out.println("All tags are correctly matched.");
        }
    }

    private static List<String> extractTags(String line) {
        List<String> tags = new ArrayList<>();
        int position = 0;

        while ((position = line.indexOf('<', position)) != -1) {
            int closingBracket = line.indexOf('>', position);
            if (closingBracket == -1) {
                break;
            }
            tags.add(line.substring(position, closingBracket + 1));
            position = closingBracket + 1;
        }

        return tags;
    }

    private static boolean isSelfClosing(String tag) {
        return tag.equals("<br>") || tag.equals("<img>");
    }

    private static boolean isOpeningTag(String tag) {
        return !tag.startsWith("</");
    }

    private static boolean isClosingTag(String tag) {
        return tag.startsWith("</");
    }

    private static boolean tagsMatch(String openTag, String closeTag) {
        return closeTag.equals("</" + openTag.substring(1));
    }
}
