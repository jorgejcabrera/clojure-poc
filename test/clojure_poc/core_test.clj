(ns clojure-poc.core-test
  (:require [clojure.test :refer :all]
            [clojure-poc.core :refer :all]))

;; Definir la función slice que reciba una cadena cad y un número n y devuelva una lista con todas las
;; subcadenas contiguas de cad cuyo tamaño sea n. Por ejemplo: (slice "abcde" 3) → ("abc" "bcd" "cde")
;; Definir una función para transponer una lista de listas
(defn transponer [listas]
  (map list listas)
  )

;; Definir una función para producir una lista con los elementos en las posiciones pares de dos listas dadas.
(defn posiciones-pares [l]
  (map second (partition 2 l))
  )

(defn combinar-posiciones-pares [l1 l2]
  (concat (posiciones-pares l1) (posiciones-pares l2))
  )

;; Definir una función para eliminar las ocurrencias de un dato escalar en una lista (a todo nivel).
(defn remove [x l]
  (cond
    (empty? l) l
    (coll? (first l))
    (cons
      (remove x (first l))
      (remove x (rest l))
      )
    (= x (first l))
    (remove x (rest l))
    :else (cons
            (first l)
            (remove x (rest l))
            )
    )
  )

;; Definir la función tercer-angulo que reciba los valores de dos de los ángulos interiores de un triángulo
;; y devuelva el valor del restante.
(defn third-angle [x1 x2]
  (- 180 x1 x2)
  )

;; Definir la función segundos que reciba los cuatro valores (días, horas, minutos y segundos) del tiempo que
;; dura un evento y devuelva el valor de ese tiempo expresado solamente en segundos.
(defn as-seconds [d h m s]
  (if (and (>= d 0) (>= h 0) (>= m 0) (>= s 0))
    (+ (* 24 60 60 d) (* 60 60 h) (* 60 m) s)
    (print (str "Horario invalido")))
  )

;; Definir la función sig-mul-10 que reciba un número entero y devuelva el primer múltiplo de 10 que lo supere.
(defn sig-mult10 [n]
  (if (> (rem n 10) 0)
    (sig-mult10 (+ n 1))
    n)
  )

;; 	Definir la función capicua
(defn is-reversible [n]
  (= (reverse (str n)) (seq (str n)))
  )

(defn parse-int [s]
  (Integer. (re-find #"\d+" s)))

(defn invertir [n]
  (parse-int (apply str (reverse (str n)))))

(defn fib
  ([n]
   (fib [0 1] n))
  ([x, n]
   (if (< (count x) n)
     (fib (conj x (+ (last x) (nth x (- (count x) 2)))) n)
     x)))

(defn nth-fib [n]
  (apply + (fib n))
  )

(defn cant-dig [n]
  (count (re-find #"\d+" (str n)))
  )

(defn pow [x n]
  (if (zero? n)
    1
    (* x (pow x (dec n)))))

(defn recursive-pot?
  [n m exp]
  (if (> (pow m exp) n)
    false
    (if (= n (pow m exp))
      true
      (recursive-pot? n m (+ exp '1))
      )
    )
  )

(defn pot? [n m]
  (recursive-pot? n m 1)
  )

(defn digits [n]
  (if (pos? n)
    (conj (digits (quot n 10)) (mod n 10))
    []))

(defn diff [s1 s2]
  (mapcat
    (fn [[x n]] (repeat n x))
    (apply merge-with - (map frequencies [s1 s2]))))

(defn pair-position
  [l]
  (difference l (take-nth 2 l))
  )


(deftest testing-functions

  ;  (testing "cuando convertimso una lista a string, el resultado debería ser el esperado"
  ;    (is (= (as-string '((\a \b) (\b \c) (\c \d) (\d \e))) '("ab" "bc" "cd" "de")))
  ;    )

  ;(testing "cuando transpongo una matriz, el resultado debería ser el esperado"
  ;  (is (= (transponer '((1 2 3) (1 2 3) (1 2 3))) '((1 1 1) (2 2 2) (3 3 3))))
  ; )
  (testing "pair-position debería retornar solo las posiciones pares"
    (is (= (pair-position '(1 2 3 4 5 6)) '(2 4 6)))
    )
  (testing "digs debería funcionar bien para 456579"
    (is (= (digits '456579) '(4 5 6 5 7 9)))
    )
  (testing "digs debería funcionar bien para 1234"
    (is (= (digits '123) '(1 2 3)))
    )
  (testing "65 es potencia de 2"
    (is (= (pot? '65 '2) false))
    )
  (testing "64 es potencia de 2"
    (is (= (pot? '64 '2) true))
    )
  (testing "16 es potencia de 2"
    (is (= (pot? '16 '2) true))
    )
  (testing "elevar 4 al cuadrado debe dar 16"
    (is (= (pow '4 '2) '16))
    )
  (testing "la cantidad de digitos deberia funcionar bien con un numero entero"
    (is (= (cant-dig '123.4) '3))
    )
  (testing "la cantidad de digitos deberia funcionar bien con un numero entero"
    (is (= (cant-dig '1234) '4))
    )
  (testing "fibonacci de 3 debe dar bien"
    (is (= (nth-fib '3) '2))
    )
  (testing "cuando invierto un número debería obtener el resultado esperado"
    (is (= (invertir '123) '321))
    )
  (testing "cuando combino posiciones pares de dos listas, el resultado debería ser el esperado"
    (is (= (combinar-posiciones-pares '(1 2 3 4) '(5 6)) '(2 4 6)))
    )
  (testing "cuando trato de obtener posiciones pares de una lista chica, el resultado debería ser vacío"
    (is (= (posiciones-pares '(1)) '()))
    )
  (testing "cuando obtengo posiciones pares de una lista, deberían obtnerse los valores esperados"
    (is (= (posiciones-pares '(1 2 3 4 5 6)) '(2 4 6)))
    )
  (testing "when removes a duplicated number from a collection, then all of those must be removed"
    (is (= (remove 1 '(2 1 1 3)) '(2 3)))
    )
  (testing "when try to remove a number from an empty collection, then no one item must be removed "
    (is (= (remove 1 '()) '()))
    )
  (testing "when use third-angle function, then the result must be expected"
    (is (= (third-angle 90 20) 70)
        )
    )
  (testing "when use third-angle function with zero angle, then the result must be 180"
    (is (= (third-angle 0 0) 180))
    )
  (testing "when convert 60 seconds to seconds, then the result must be the expected"
    (is (= (as-seconds 0 0 0 60) 60))
    )
  (testing "when find the following mult10 of 11 then 20 must be returned"
    (is (= (sig-mult10 11) 20))
    )
  (testing "when find the following mult10 of 1 then 10 must be returned"
    (is (= (sig-mult10 11) 20))
    )
  (testing "the number 123321 must be reversible"
    (is (= (is-reversible 123321) true))
    )
  (testing "the number 11321 must not be reversible"
    (is (= (is-reversible 11321) false))
    )
  )