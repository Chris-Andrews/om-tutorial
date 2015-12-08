(ns om-tutorial.A-Introduction
  (:require-macros
    [cljs.test :refer [is]]
    )
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [devcards.core :as dc :refer-macros [defcard defcard-doc]]
            ))

(defcard-doc
  "# Introduction

  This tutorial will walk you through the various parts of Om 1.0 (alpha). In order to get the most from this
  tutorial, you should understand the general goals of Om (next):

  - Make it possible to localize application state in a single client database abstraction (e.g. single top-level atom
  holding a map)
  - Provide a mechanism whereby clients can make precise non-trivial reads simply and communicate non-trivial operations simply.
  - Eliminate the need for event models
  - Provide a synchronous programming model

  There are others, but this is sufficient for a starting point.

  Note that you can navigate to the table of contents any time using the `devcards` link at the top of the page.

  ## About This Tutorial

  This tutorial is written in Bruce Hauman's excellent Devcards. As such, these documents are live code!

  This file, for example, is in `src/tutorial/om_tutorial/A_Introduction.cljs`. If you followed the README to start
  up this project, then you're reading this file through your browser and Bruce's other great tool Figwheel. The
  combination of the two bring you documentation that runs and also hot reloads whenever the files are saved.

  If you open this file in an editor, edit it and save, you'll see the browser automatically refresh the view.

  The box below, for example, if generated by a devcard:

  ")

(defcard sample-card
         (dom/div nil "The following number is calculated: " (+ 3 4))
         )

(defcard-doc
  "
  Open up the A_Introduction.cljs, search for `sample-card`, edit the numbers, save, and watch this page refresh. You
  are encouraged to play with the source code and examples in the tutorial to verify your understanding as you read.
  Devcards support state as well, and will track it in an atom for you. Thus, you can generate UI that actually responds
  to user interaction:
  ")
(defcard interactive-card
         (fn [state-atom owner]                             ;wrapper function that can accept a state atom
           (dom/div nil "A single top-level element."
                    (dom/span nil (str "value of x: " (:x @state-atom)))
                    (dom/br nil)
                    (dom/button #js {:onClick #(swap! state-atom update-in [:x] inc)} "Click me")
                    ))
         {:x 2}                                             ; This is a map of initial state that devcards puts in an atom
         {:inspect-data true}                               ; options....show me the current value of the data
         )

(defcard-doc
  "
  Notice that if you edit the code in the card above and save that it *does not* lose track of state. Figwheel does hot
  code reloading and devcards is therefore able to hold onto the state of the component. Thus, if you make dramatic
  changes to something and the saved state no longer makes sense then you will need to reload the page via the browser
  to clear that state.

  # IMPORTANT IF YOU GET STUCK:

  First, if there is no obvious error in the browser try reloading the page.

  If you make a typo or language error Figwheel will usually describe it pretty well in the browser.
  However, it is possible to get the whole thing stuck. Typing `(reset-autobuild)` in the REPL will clean the sources and
  rebuild (and you'll see compile errors there). Correct the errors and everything should start
  working again. DO NOT kill the REPL and restart, as that will cause you a lot of waiting as
  you get compile errors, edit, and restart. (If you do kill the REPL,
  you might even consider using git to undo your changes so that it will restart cleanly).

  ## Notes on documentation:

  Om wrappers on plain DOM elements take as their second parameter a javascript map (not a cljs one) or nil. As such, you
  usually write your UI like this:

  ```
  (dom/div #js {:onClick (fn [evt] ...) })
  ```

  but in many of the examples you'll see this instead:

  ```
  (dom/div (clj->js {:onClick (fn [evt] ...) }))
  ```

  Devcards has a really cool feature where you can pull live source into the documentation. Unfortunately, the
  mechanism it uses to do this (the `cljs.repl/source` function) cannot currently handle reader tags. So, in
  some examples I'm using the `cljs->js` function instead to make sure the docs and source stay in sync. I
  feel the latter is more important than the former, and once the source pulling is fixed it will be easy to
  fix the source and have all of the documentation automatically update.

  ## General Components of Om

  The following significant areas of Om must be understood in order to write a non-trivial application.

  - Building the UI.
  - Queries and the Query Grammar.
  - Colocating query fragments on stateful UI component (for composition and local reasoning).
  - The client-local app state database.
  - Turning the Queries into data for your UI.
  - Turning the Queries into remote requests for data.
  - Processing incoming responses to remote requests.
  - Dynamically changing Queries

  [Let's start with the UI.](#!/om_tutorial.B_UI)

  ")
