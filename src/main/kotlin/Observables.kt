import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

// 생산자는 데이터를 생산해서 전달하는 역할을 함
// 소비자를 등록하는 방법 두 가지

fun main() {

    // 1. Observer 방식
    // Observer 인터페이스를 구현한 객체를 Subscribe 해서 소비자를 추가
    // subscribe 의 반환 타입은 Unit 임

    val observer = object : Observer<Int> {
        override fun onComplete() {
            // Observable 이 완료된 경우
        }

        override fun onSubscribe(d: Disposable) {
            // Observable 이 데이터 전달할 준비가 되었을 때.
            // 작업 취소를 위한 Disposable 에 대한 레퍼런스를 여기서 받음
        }

        override fun onNext(t: Int) {
            // Observable 이 데이터를 전달할 때 호출
        }

        override fun onError(e: Throwable) {
            // Observable 이 에러를 전달할 때 호출. Error 시 Complete 없이 종료
        }
    }

    Observable.just(1, 2, 3, 4).subscribe(observer)


    // 2. Consumer 방식
    // 각각의 Consumer 를 subscribe 해서 소비자를 추가 (주로 이것을 사용한다고 함)
    // Consumer 는 메소드 한 개짜리 자바 인터페이스이므로 SAM 을 통해 람다로 표현 가능
    // subscribe 의 반환 타입이 Disposable 임

    val disposable: Disposable = Observable.just(1, 2, 3, 4)
        .subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") }
        )
}

