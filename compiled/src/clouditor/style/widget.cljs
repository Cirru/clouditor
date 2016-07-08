
(ns clouditor.style.widget
  (:require [hsl.core :refer [hsl]]))

(def button
 {:line-height 2,
  :color (hsl 0 0 100),
  :background-color (hsl 200 70 70),
  :padding "0 8px"})

(def expression
 {:border-style "solid",
  :min-height "40px",
  :border-width "1px",
  :padding "4px",
  :outline "none",
  :border-color (hsl 0 0 90),
  :margin-right "8px"})

(def expression-box {:display "inline-block", :position "relative"})

(def float-toolbar
 {:top "100%",
  :background-color (hsl 200 80 50 0.2),
  :padding "8px",
  :position "absolute",
  :left 0})

(def tool-button
 {:line-height "2",
  :color (hsl 0 0 100),
  :font-size "12px",
  :background-color (hsl 200 80 70),
  :cursor "pointer",
  :padding "0 4px",
  :font-family "Helvetica"})

(def token
 {:line-height 2,
  :font-size "14px",
  :background-color (hsl 0 0 90),
  :padding "0 4px",
  :outline "none",
  :border "none",
  :font-family "Menlo,monospace"})

(def token-box
 {:display "inline-block", :position "relative", :margin-right "8px"})
