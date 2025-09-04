# Enye Botha's CPSC 210 Project

## The Language Learning Project Proposal

This project aims to be a tool used in language learning, recording the sentences and words a user is trying to learn, further classifying them as **Completed** and **In Progress**. It will test the user on the words they are trying to learn, the words they have learnt, and words they have more difficulty with, all while logging each word to a category accordingly. The target audience will be young children/their parents to young adults practicing a language, my hope is that the design of this application can also serve as a tool for children with speech apraxia, who struggle with the sounds of certain words and could use a simple application to record and test their progress. The reason I am passionate about this project is because someone very close to my heart grew up with speech apraxia, I saw how hard she worked to be able to communicate today, and this tool may have been useful in her journey. 

Notes about this Project:
- This project will serve as a *prototype* for further development in the future.
- This project will be catered more towards younger children in it's design, but generally used by young adults/language learners, speech therapists, and the parents of children with speech apraxia.
- A **Word** is a class with fields containing the word in the user's native language, the translation of the word into the target language, the pronounciation of the word, and if the word is classified as easy/hard (for the user). 


## User Stories

- As a user, I want to be able to add a word to my list of *Words to Learn*.
- As a user, I want to be able to delete a word from my list of *Words to Learn*.
- As a user, I want to be able to move a word from my *list of Words to Learn* to my list of *Completed Words*. 
- As a user, I want to be able to view a list of my *Words to Learn*. 
- As a user, I want to be able to view the size of the *Completed Words* list. 
- As a user, I want to be able to test myself on a random word from my list of *Words to Learn*.
- As a user, I want to be able to save my *list of Word to Learn* and *Completed Words*.
- As a user, I want to be able to load my *list of Words to Learn* and *Completed Words* from file.

# Instructions for End User

- To add a word to your log fill out the input fields at the bottom of the application, then press the 'Add Word' button.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the 'Test Word' Button, then if you would like to see the answer, enter "y" or "n" in the input field, then click the 'Enter' button; this tests your on a random word from your log.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the 'Search Word' button, entering the word you would like to search into the input field, and then clicking the 'Enter' button; this finds and displays a word from your log.
- You can locate my visual component by clicking the 'Add Word' button (see the top right of the application).
- You can save the state of my application by clicking the 'Save' button on the right of the application.
- You can reload the state of my application by clicking the 'Load' button on the right of the application.

- To view your log click the 'View List' Button.
- To mark a word as easy, click the 'Mark Easy' button, enter the word in the input field, and click the 'Enter' button.
- To view your progress/# of words learnt, click the 'View Progress' button.
- To remove a word click the 'Delete Word' Button, enter the word you want to delete into the input field, and click the 'Enter' button.




# Phase 4: Task 2

Mon Nov 25 20:33:19 PST 2024
created a new word
Mon Nov 25 20:33:19 PST 2024
added a new word
Mon Nov 25 20:33:22 PST 2024
returned a random word
Mon Nov 25 20:33:27 PST 2024
converted log to list of words in string format
Mon Nov 25 20:33:32 PST 2024
deleted a word from word log
Mon Nov 25 20:33:38 PST 2024
created a new word
Mon Nov 25 20:33:38 PST 2024
added a new word
Mon Nov 25 20:33:43 PST 2024
searched a word from the word log
Mon Nov 25 20:33:47 PST 2024
marked word as easy!
Mon Nov 25 20:33:47 PST 2024
deleted a word from word log
Mon Nov 25 20:33:54 PST 2024
converted learnt words log to JSON array
Mon Nov 25 20:33:54 PST 2024
converted word to JSON object
Mon Nov 25 20:33:54 PST 2024
converted word log to JSON array
Mon Nov 25 20:33:54 PST 2024
added word log to JSON object
Mon Nov 25 20:33:54 PST 2024
added words learnt to JSON object


# Phase 4: Task 3

I would refactor my design by putting the two ArrayLists of words in the WordBank class into a single HashMap, reducing the two associations to one. Furthermore, I would have my LanguageAppGUI class extend the LanguageApp class, overriding the necessary methods (particularily the methods to save the wordBank and Load the wordBank), thus I can rather reference the writer, reader, and wordBank objects already created in that class, rather than creating new redundant instances.