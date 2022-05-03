namespace ApiHeroku.Exceptions
{
    public class PostRequestException : Exception
    {
        public PostRequestException()
        {
        }

        public PostRequestException(string? message) : base(message)
        {
        }
    }
}
