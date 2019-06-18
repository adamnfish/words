Words
=====

Generate solutions to anagams puzzle, e.g. Wordscapes.

## Usage

From the root of the project, with `sbt` installed:

    sbt run <chars>

e.g.

    sbt run abcdef

## Note

Dictionary is hard-coded to try Ubuntu and OSX's default location for the
Unix words file (and uses the british english file in the former case).
