namespace ApiHeroku.Exceptions
{
    public class GetException : Exception
    {

        public GetException()
        {
        }

        public GetException(string? message) : base(message)
        {
        }
    }
}
