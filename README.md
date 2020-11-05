# 1. 개요
- AOP를 사용하여 암호화 처리 부분과 Database 등의 처리 부분을 완전히 분리합니다.
- 다양한 암호화 및 해쉬 알고리즘 수용 가능합니다.
- 기존의 Database 처리 및 비지니스 로직을 거의 수정하지 않고도 암/복호화 처리 가능합니다.
- Annotation을 사용하여 필요한 Column 및 필드만 암/복호화 처리 가능합니다.


# 2. 기술명세
- 언어 : Java 1.6 이상
- IDE : STS 4
- 프레임워크 : spring 3.2.4
- 의존성 & 빌드 관리 : maven
- 기타 : spring aop 사용


# 3. 업무흐름도

> AOP 암호화 기능 

<img src="https://user-images.githubusercontent.com/61044774/90595431-d6965600-e227-11ea-968c-eec8a0c45a9b.jpg" width="100%"></img>


# 4. 사용예제

## 암호화 AOP 적용순서 1 

- 암호화 대상 Class Object의 Field에 암호화 관련 설정
```java

public class CreditCard extends AbstractBean implements Serializable {

	private long id;

	@Encrypt(algorithm=EncryptAlgorithm.AES128CBC, secureKey="1234567890123456", secureIV="9878543210123456")
	private String creditCardNumber;

	@Encrypt(algorithm=EncryptAlgorithm.BlowfishECB, secureKey="MILKSECURETESTKEY", secureIV="MILKSECUREKEYIV12")
	private String fourDigits;

	@Hash(algorithm=HashAlgorithm.SHA256)
	private String password;
	
	private String cardType;
	private String nameOnCard;

```
* 암호화 및 Hash 처리가 필요한 필드에 **@Encrypt, @Hash** 등과 같은 Annotation을 선언한다.
* **@Encrypt** : 암호화 처리 Annotation
  * algorithm : AES128, Blowfish 등 대칭키 알고리즘을 지정할 수 있다.
  * secureKey : 암호화 키값을 지정할 수 있다.
  * secureIV : 암호화 IV값을 지정할 수 있다.
* **@Hash** : 해싱처리 Annotation
  * algorithm : sha, md5 등과 같은 해시 알고리즘을 지정할 수 있다.

## 암호화 AOP 적용순서 2

- 암호화를 수행할 Class 의 Method에 암호화 관련 설정
```java

	@Override
	@DoEncryption
	public void encryptFieldCreditCard(CreditCard creditCard) throws Exception {
		// do something! 
	}

	@Override
	@DoEncryption(type=EncryptType.Both)
	public CreditCard encryptBothFieldCreditCard(CreditCard creditCard,
		// do something!
	}

```
* 암호화를 수행하고자 하는 Class내 Method 구간에서 **@DoEncryption** Annotation을 선언한다.
</br>이때 해당 Method의 인자의 Object의(예제에서는 CreditCard) Field 가 암호화 처리 Annotation이 지정되어 있을때 
   해당 필드를 암/복화를 수행한다.  
<span style="color:blue">**@DoEncryption** Annotation을 선언되어 있지 않은 메소드에서는 1의 암호화대상 Class일 지라도 암복호화가 수행되지 않는다.</span>
* **@DoEncryption** : 암호화 처리 AOP 수행 식별자 (3가지 **type**의 암호화 처리를 지원한다.)
  * encrypt : 해당 필드를 암호화만 수행한다. Return 되는 값은 복호화 되지 않는다. (default 가 encrypt 모드이다.)
  * decrypt : 해당 필드의 복호화만 수행한다. 인자값은 암호화 시키지 않고 다만 Return 되는 값만 복호화 시킨다.
  * both : 해당 필드의 암/복호화를 모두 수행한다.
* 앞에 암호화 AOP 적용순서 1에서 **@Hash** Annotation일 경우 encrypt/decrypt 모드와 상관없이 무조건 Hashing 처리한다.

 
# 5. 암호화 시험결과

> 성능시험결과

- DB에 등록되는 정보를 암호화 AOP 적용 및 미적용으로 분류하여 성능을 시험하였다.
- 100개의 클라이언트에서 10000번의 루프를 돌며 실행한 결과를 추출하였다. (JMeter 사용)
- 암호화 알고리즘은 blowfish 를 사용하였다. (대칭형 가변키길이 암호화 방식)
- 설계한 시험환경에서는 암호화 AOP 적용에 대한 성능 저하가 미미한 것으로 보인다.
 
<img src="https://user-images.githubusercontent.com/61044774/90596676-6c32e500-e22a-11ea-8924-5fa62715ba80.jpg" width="110%"></img> 