(ns clojure-poc.exercise-test-1
  (:require [clojure.test :refer :all]))


; Dada una lista de listas que contienen T y F (valores booleanos),
; hay que quedarse con los índices de las listas que tienen más T que F.
; truth_function: <<T T F><F F F><T F><T T T T F>> → <1 4>
(defn has_more_t?
  [l]
  (if (empty? l)
    false
    (let [freq (frequencies l)]
      (>
        (if (contains? freq "t") (freq "t") '0)
        (if (contains? freq "f") (freq "f") '0)
        )
      )
    )
  )

(defn count-index
  [listOfList index]
  (if (false? (empty? listOfList))
    (let [l (first listOfList)]
      (concat
        (if (has_more_t? l) (list index))
        (count-index (rest listOfList) (inc index))
        )
      )
    )
  )

(deftest testing-function
  (testing "count index debería devolver una lista de los índices con más t que f"
    (is (= (count-index '(("t" "t" "f") ("f" "f") ("t")) '0) '(0 2)))
    )
  (testing "map index debería funcionar bien cuando hay mas t que f"
    (is (= (has_more_t? '("t" "f" "t")) true))
    )
  )
