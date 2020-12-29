import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main() {

    // 생성 연산자

    // 1. create
    // - 함수 내부에서 emitter 가 직접 onNext, onComplete 등으로
    //   데이터를 전달하는 연산자

    Observable.create<String> { emitter ->
        emitter.onNext("Hello")
        emitter.onNext("RxJava")
        emitter.onComplete()
    }.subscribe { println(it) }
    println()


    // 2. defer
    // - ObservableSource 를 리턴하는 Callable 을 받는 연산자

    Observable.defer {
        Observable.create<String> {
            it.onComplete()
        }
    }.subscribe(::println)
    println()


    // 3. from
    // - Array. Iterable, Callable 로부터 Observable 만드는 연산자

    val items = arrayOf("Hello", "World!")
    Observable.fromArray(*items).subscribe(::println)
    println()


    // 4. interval
    // - 주어진 주기대로 0부터 1씩 증가된 값을 만드는 연산자

    Observable.interval(100, TimeUnit.MILLISECONDS)
        .subscribe(::println)
    Thread.sleep(555)
    println()


    // 5. just
    // - 최대 10개의 데이터를 전달하는 연산자

    Observable.just(1, 2, 3).subscribe(::println)
    println()


    // 6. range
    // - range(start, cnt) : start 부터 cnt 만큼 1씩 증가한 데이터 전달하는 연산자

    Observable.range(3, 2).subscribe(::println)
    println()


    // 7. repeat
    // - Observable 을 지정한 횟수만큼 반복시키는 연산자 (subscribe 포함)

    val observable = Observable.just("Hello", "World!")
        .repeat(2)

    observable.subscribe(::println)
    println()


    // 8. timer
    // - 정해진 시간 후 0을 전달하는 Observable 을 반환

    println("Start : ${System.currentTimeMillis()}")
    Observable.timer(1000, TimeUnit.MILLISECONDS)
        .subscribe {
            println("Start : ${System.currentTimeMillis()} + $it")
        }
}