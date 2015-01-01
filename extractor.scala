//extractor.scala

object EMail{
	def apply(user : String, domain : String) = user + "@" + domain
	def unapply(whole : String) : Option[(String,String)] = {
		val parts = whole split "@"
		if (parts.length == 2) Some(parts(0),parts(1)) else None
	}
}

object Domain{
	def apply(parts : String*) = parts.reverse.mkString(".")
	def unapplySeq(whole : String) : Option[Seq[String]] = {
		val parts = whole split "\\."
		if (parts.contains("")){ 
			// "hotmail.como.".split("\\.") is ("hotmail","com") but not ("hotmail","com",""). W.T.F? java sucks.
			None
		} else{
			Some(parts.reverse)
		}
	}
}

println(EMail("sioncheng","hotmail.com"))
println(EMail.unapply("sioncheng@hotmail.com"))
println(Domain("a","b","c"))
println(Domain.unapplySeq("www.hotmail.com"))

def isSomebodyInDomain (somebody : String , emailAddress : String) : Boolean = {
	EMail.unapply(emailAddress) match {
		case Some((a,b)) => {
			Domain.unapplySeq(b) match {
				case _: Some[_] if (somebody == a) => true
				case _ => false 
			}
			
		}
		case _ => {
			false
		}
	}
}

def isTomInDotCom(whole : String) : Boolean = whole match{
	case EMail("tom",Domain("com",_*)) => true
	case _ => false
}

println(isSomebodyInDomain("sioncheng","sioncheng@hotmail.com."))
println(isTomInDotCom("tom@a.com"))
println(isTomInDotCom("tom@a.net"))