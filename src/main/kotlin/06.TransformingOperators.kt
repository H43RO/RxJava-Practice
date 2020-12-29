import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {

    // 변환 연산자

    // 1. buffer
    // - buffer(count: Int, skip: Int) 형태로 count 만큼 데이터가 모이면 한 번에 전달함
    // - count 까지 포함해서 skip 만큼의 데이터는 버림 (count < skip 이면 차이만큼 데이터 무시)
    // - 마지막 남는 데이터는 count 만큼 차지 않아도 전달함

    Observable.fromIterable(0..8)
        .buffer(2, 4)
        .subscribe(::println)
    println()


    // 2. map
    // - 데이터를 변환, 가공하는 연산자

    Observable.fromIterable(0..3)
        .map { "RxJava : $it" }
        .subscribe(::println)
    println()


    // 3. xxxMap
    // - Observable 을 받아 새로운 Observable 을 만드는 연산자
    // - flatMap : 데이터를 병렬적으로 처리
    // - concatMap : 데이터를 직렬적으로 처리
    // - switchMap : 중간에 데이터가 들어오면 무시

    Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
        .flatMap {
            Observable.just("$it plusplus")
                .delay(Random.nextLong(1), TimeUnit.SECONDS)
        }
        .subscribe(::println)
    println()


    Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
        .concatMap {
            Observable.just("$it plusplus")
                .delay(Random.nextLong(1), TimeUnit.SECONDS)
        }
        .subscribe(::println)
    println()


    Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
        .switchMap {
            Observable.just("$it plusplus")
                .delay(Random.nextLong(1), TimeUnit.SECONDS)
        }
        .subscribe(::println)
    println()

    Thread.sleep(5000)


    // 4. scan
    // - 이전 데이터와 현재 데이터를 조합하여 데이터를 전달하는 연산자
    // - 첫 데이터는 그대로 전달함 (reduce 로 이해하면 됨)

    Observable.fromIterable(0..3)
        .scan{t1, t2 -> t1 + t2}
        .subscribe(::println)
}