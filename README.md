UI mistakes, that i found:

1. There is a different margins between texts and dividers=>I set for all them a single value in "dimensions",
so it looks correctly

2. I find that one divider is thinner than others=> I make a layout "divider" and include it between lines

3. Back button close the app, so back button in toolbar is not needed, cause it must return to previous activity. 

4. Differnt languages on your activity, so I made three language support(english, russian and ukrainian).

5. I think they are no more...

Also, I know that Yalantis uses MVP patern to all apps, that you do. So I tried to realize it by creating class MyOnClickListener,
that sets onClicListener to controls, and on click gets name of control and send it to activity. Maybe something wrong, but I tried))
