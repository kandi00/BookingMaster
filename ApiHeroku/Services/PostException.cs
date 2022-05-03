using System.Runtime.Serialization;

namespace ApiHeroku.Services
{
    [Serializable]
    internal class PostException : Exception
    {
        public PostException()
        {
        }

        public PostException(string message) : base(message)
        {
        }

        public PostException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected PostException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}