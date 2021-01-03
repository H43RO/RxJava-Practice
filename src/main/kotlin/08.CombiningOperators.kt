import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

fun main() {

    // 결합 연산자

    // 1. combineLatest
    // - 각각 Observable 에 데이터 생성될 때 데이터를 조합해서 전달하는 연산자

    val observable1 = Observable.interval(1000L, TimeUnit.MILLISECONDS)
    val observable2 = Observable.interval(750L, TimeUnit.MILLISECONDS).map { it + 10000 }

    Observable.combineLatest<Long, Long, String>(
        observable1, observable2,
        BiFunction { t1, t2 ->
            "$t1 $t2"
        }
    ).subscribe {
        println(System.currentTimeMillis())
        println(it)
    }
    Thread.sleep(3000)


    // 2. merge
    // - 각각 Observable 을 단순히 합치는 연산자
    // - mergeWith 으로 이어 붙일 수도 있음

    val observableA = Observable.interval(0, 1000, TimeUnit.MILLISECONDS).map { "1:$it" }
    val observableB = Observable.interval(0, 500, TimeUnit.MILLISECONDS).map { "2:$it" }
    val observable = Observable.merge(observableA, observableB)
    observable.subscribe {
        println(it)
    }


    // 3. zip
    // - Observable 에서 생성한 데이터 순서에 맞게 조합하는 연산자

    val observableX = Observable.just(1, 2, 3, 4, 5, 6, 7)
    val observableY = Observable.just("a", "b", "c", "d", "e", "f")
    val observableZ = Observable.zip(
        observableX, observableY,
        BiFunction<Int, String, String> { t1, t2 ->
            "$t1 and $t2"
        }
    )
    observableZ.subscribe(::println)


    // 4. startWith
    // - Observable 에 첫 번째 데이터를 추가

    Observable.just(1, 2, 3, 4, 5)
        .startWith(500)
        .subscribe(::println)
}