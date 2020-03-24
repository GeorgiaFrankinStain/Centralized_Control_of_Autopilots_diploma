<center><h1>Extensions to the standard Java CodeStyle.</h1></center>
<center><h1>===================================</h1></center>


# TITLE FOLDER:
* **Folder names starts with "_" - content for developer. (If you want to keep it all in your memory).** 
* **Folder names starts with "__" - content for program of developer.**

* **Usual title - content for developer and those interested project.**




# CODE:
<details><summary>The sequence content of the class.</summary><ul>

<li>(1) Variable</li>
<li>(2) Public methods (with the exeption of Getter and Setter).</li>
<details><summary>(3) Private methods.</summary>

+ marking
<ul>

```
//==== <start> <Private_Methods> =======================================================================
//==== <end> <Private_Methods> =========================================================================
```
</ul></details>



<details><summary>(4) Getter and Setter are written at the end of class.</summary>

+ because:
    - The privacy of the field and its purpose can tell whether there is a getter and a setter.
    - Getter and setter are searched for by text search.
+ marking
<ul>

```
//==== <start> <Getter_and_Setter> ==================================================
//==== <end> <Getter_and_Setter> ==================================================
```


</ul></ul></details>


</details>















<details><summary>Comment FIXME and TODO stantarts</summary><ul>
The text before the separator "--" is used in the code.

```
//FIXME FORMALITY  -- (Optional, because it is too simple. Exclusively for beauty).
//FIXME CRITICAL
//FIXME REALISED


//TODO REALISED
```

</ul></details>






<details><summary>Comment for tests (marking of test).</summary><ul>
The text before the separator "--" is used in the code.

```
//==== <start> <assertTrue> ====================
//==== <end> <assertTrue> ====================


//==== <start> <assertFalse> ====================
//==== <end> <assertFalse> ====================
```

</ul></details>



____________________________________________

* Always write the word this with variable of class (this.variableClass).




